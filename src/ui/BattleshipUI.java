package ui;

import battleship.*;
import network.TCPStream;
import network.TCPStreamCreatedListener;
import view.BattlefieldPrinter;
import view.PrinterInterface;


import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static battleship.OrientationValues.HORIZONTALLY;
import static battleship.OrientationValues.VERTICALLY;
import static battleship.ShipValues.*;


public class BattleshipUI implements TCPStreamCreatedListener {
    private static final String PRINT = "print";
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String SET = "set";
    private static final String SHOOT = "shoot";
    private static final String RULES = "rules";
    private final PrintStream outStream;
    private final BufferedReader inBufferedReader;
    private final String PLAYERNAME;
    private final int DEFAULT_PORT = 6907;
    private final ArenaImpl arenaOwn;
    private TCPStream tcpStream;
    private RadioStation radioStation;
    private PrinterInterface printer;
    private String opponentName;


    public static void main(String[] args) {
        System.out.println("Welcome to Battleship version 0.1");

        if (args.length < 1) {
            System.err.println("need playerName as parameter");
            System.exit(1);
        }

        System.out.println("Welcome " + args[0]);
        System.out.println("Let's play a game");

        BattleshipUI userCmd = new BattleshipUI(args[0], System.out, System.in);

        userCmd.printUsage();
        userCmd.runCommandLoop();
    }

    public BattleshipUI(String playerName, PrintStream os, InputStream is) {
        this.PLAYERNAME = playerName;
        this.outStream = os;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(is));
        this.arenaOwn = new ArenaImpl(playerName);
        this.printer = new BattlefieldPrinter();



    }
    public void printUsage() {
        StringBuilder b = new StringBuilder();

        b.append("\n");
        b.append("\n");
        b.append("valid commands:");
        b.append("\n");
        b.append(CONNECT);
        b.append(".. connect as tcp client");
        b.append("\n");
        b.append(OPEN);
        b.append(".. open port become tcp server");
        b.append("\n");
        b.append(PRINT);
        b.append(".. print board");
        b.append("\n");
        b.append(SET);
        b.append(".. set a ship of choice");
        b.append("\n");
        b.append(SHOOT);
        b.append(".. shoot on position");
        b.append("\n");
        b.append(RULES);
        b.append(".. how to play");
        b.append("\n");
        b.append(EXIT);
        b.append(".. exit");

        this.outStream.println(b);
    }

    public void runCommandLoop() {
        boolean again = true;

        while (again) {
            String cmdLineString = null;

            try {
                // read user input
                cmdLineString = inBufferedReader.readLine();

                // finish that loop if less than nothing came in
                if (cmdLineString == null) break;

                // trim whitespaces on both sides
                cmdLineString = cmdLineString.trim();

                // extract command
                int spaceIndex = cmdLineString.indexOf(' ');
                spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

                // got command string
                String commandString = cmdLineString.substring(0, spaceIndex);

                // extract parameters string - can be empty
                String parameterString = cmdLineString.substring(spaceIndex);
                parameterString = parameterString.trim();

                // new edited (new options SHOOT and RULES added to switch)
                // start command loop
                switch (commandString) {
                    case PRINT:
                        this.doPrint();
                        break;
                    case CONNECT:
                        this.doConnect(parameterString);
                        break;
                    case OPEN:
                        this.doOpen();
                        break;
                    case SET:
                        this.doSet(parameterString);
                        // redraw
                        this.doPrint();
                        break;
                    case SHOOT:
                        this.doShoot(parameterString);
                        // redraw
                        this.doPrint();
                        break;
                    case RULES:
                        this.doRules();
                        break;
                    case "q": // convenience
                    case EXIT:
                        again = false;
                        this.doExit();
                        break; // end loop

                    default:
                        this.outStream.println("unknown command:" + cmdLineString);
                        this.printUsage();
                        break;
                }
            } catch (IOException ex) {
                this.outStream.println("cannot read from input stream - fatal, give up");
                try {
                    this.doExit();
                } catch (IOException e) {
                    // ignore
                }
            } catch (IllegalInputException iie) {
                this.outStream.println("wrong arguments used - see 'rules' for help");
            } catch (OutOfFieldException oofe) {
                this.outStream.println("you tried to reach not existing coordinates");
            } catch (FieldOccupiedException foe) {
                this.outStream.println("coordinates already occupied by an other ship");
            } catch (WrongStatusException wse) {
                this.outStream.println("you can't do this in this phase of the game");
            } catch (NoSuchElementException nsee) {
                this.outStream.println("you probably forgot the parameter");
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                           ui method implementations                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void doSet(String parameterString)
            throws WrongStatusException, IllegalInputException, OutOfFieldException, FieldOccupiedException ,NoSuchElementException{

        StringTokenizer st = new StringTokenizer(parameterString);
        int apixCoordinate = Integer.parseInt(st.nextToken());
        int apiyCoordinate = Integer.parseInt(st.nextToken());
        String ship = st.nextToken();
        String orientation = st.nextToken();

        ShipValues apiShip;
        OrientationValues apiOrient;

        switch (ship) {
            case "S":
                apiShip = SMALL;
                break;
            case "M":
                apiShip = MEDIUM;
                break;
            case "B":
                apiShip = BIG;
                break;
            default:
                throw new IllegalInputException();
        }

        switch (orientation) {
            case "H":
                apiOrient = HORIZONTALLY;
                break;
            case "V":
                apiOrient = VERTICALLY;
                break;
            default:
                throw new IllegalInputException();
        }

        //call api set method
        arenaOwn.set(apixCoordinate, apiyCoordinate, apiShip, apiOrient);

    }

    private void doShoot(String parameterString) throws WrongStatusException, OutOfFieldException, IllegalInputException {

        StringTokenizer st = new StringTokenizer(parameterString);
        int apixCoordinate = Integer.parseInt(st.nextToken());
        int apiyCoordinate = Integer.parseInt(st.nextToken());

        arenaOwn.shoot(apixCoordinate, apiyCoordinate);

    }

    // added (new method prints game rules and parameter options for commands)
    private void doRules() {

        this.outStream.print(
                "\n\nBATTLESHIP RULES\n\n"
                        + "Each player starts with 3 small, 2 medium and 1 big ships.\n"
                        + "In the set up phase you place your ships horizontally or vertically on your board using the 'set' command.\n"
                        + "In the game phase you alternately choose a position on your opponents field you want to 'shoot'.\n"
                        + "Your oppinents ships are invisable to you, only hits are marked on your field with H,\n"
                        + "deads with D and missed shots with x.\n"
                        + "The Player who hit all parts of all the opponent ships wins.\n\n"
                        + "COMMANDS AND PARAMETERS\n\n"
                        + "open\n"
                        + "connect   Hostname\n"
                        + "set       XCoordinate(0 - 9) YCoordinate(0 - 9) Ship(S,M,B) Orientation(H,V)\n"
                        + "shoot     XCoordinate(0 - 9) YCoordinate(0 - 9)\n"
                        + "print\n"
                        + "rules\n"
                        + "exit\n"
        );
    }


    private void doExit() throws IOException {
        this.radioStation.close();
    }

    private void doOpen() {
        if (this.alreadyConnected()) return;

        this.tcpStream = new TCPStream(this.DEFAULT_PORT, true, this.PLAYERNAME);
        this.tcpStream.setStreamCreationListener(this);
        this.tcpStream.start();
    }

    private void doConnect(String parameterString) {
        if (this.alreadyConnected()) return;

        String hostname = null;

        try {
            StringTokenizer st = new StringTokenizer(parameterString);
            hostname = st.nextToken();
        } catch (Exception e) {
            System.out.println("no hostname provided - take localhost");
            hostname = "localhost";
        }

        this.tcpStream = new TCPStream(this.DEFAULT_PORT, false, this.PLAYERNAME);
        this.tcpStream.setRemoteEngine(hostname);
        this.tcpStream.setStreamCreationListener(this);
        this.tcpStream.start();

    }

    private void doPrint() throws IOException {

        this.printer.printField(this.arenaOwn.getHitMap(), this.arenaOwn.getShipMap());


        /*
        this.gameEngine.getPrintStreamView().print(System.out);

        if (this.gameEngine.getStatus() == Status.ENDED) {
            if (this.gameEngine.hasWon()) {
                System.out.println("you won");
            } else {
                System.out.println("you lost");
            }
        } else if (this.gameEngine.isActive()) {
            System.out.println("your turn");
        } else {
            System.out.println("please wait");
        }

         */

        // print player board
        // + (if in game phase)
        // print opponent board with hits

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  guards                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void checkConnectionStatus() throws Exception {
        if (this.radioStation == null) {
            throw new Exception("not connected yet - call connect before");
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       helper: don't repeat yourself                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean alreadyConnected(){
        if (this.tcpStream != null) {
            System.err.println("connection already established or connection attempt in progress");
            return true;
        }
        return false;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              listener                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void streamCreated(TCPStream channel) {
        this.radioStation = new RadioStation(this.arenaOwn);
        this.arenaOwn.setRadioStation(this.radioStation);

        try{
            radioStation.handleConnection(channel.getInputStream(), channel.getOutputStream());
            System.out.println("connected");
        } catch (IOException e){
            System.err.println("cannot get streams from tcpStream - fatal, give up: " + e.getLocalizedMessage());
            System.exit(1);
        }

    }

    //...

}

