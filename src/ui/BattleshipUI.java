package ui;

import battleship.*;

import java.io.*;
import java.util.StringTokenizer;


public class BattleshipUI {
    private static final String PRINT = "print";
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String SET = "set";
    private static final String SHOOT = "shoot";
    private static final String RULES = "rules";
    private final PrintStream outStream;
    private final BufferedReader inBufferedReader;
    private final BoardPlayer boardPlayer;
    private final BoardOpponent boardOpponent;
    private final String playerName;
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
        this.playerName = playerName;
        this.outStream = os;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(is));

        this.boardPlayer = new BoardPlayer(playerName); //+ Parameter opponentName
        this.boardOpponent = new BoardOpponent(opponentName);



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
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                           ui method implementations                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void doSet(String parameterString)
            throws WrongStatusException, IllegalInputException, OutOfFieldException, FieldOccupiedException {

        StringTokenizer st = new StringTokenizer(parameterString);
        int apixCoordinate = Integer.parseInt(st.nextToken());
        int apiyCoordinate = Integer.parseInt(st.nextToken());
        String ship = st.nextToken();
        String orientation = st.nextToken();

        int apiShip = 0;
        int apiOrient = 0;

        switch (ship) {
            case "S":
                apiShip = 0;
                break;
            case "M":
                apiShip = 1;
                break;
            case "L":
                apiShip = 2;
                break;
            default:
                throw new IllegalInputException();
        }

        switch (orientation) {
            case "H":
                apiOrient = 0;
                break;
            case "V":
                apiOrient = 1;
                break;
            default:
                throw new IllegalInputException();
        }

        //call api set method
        boardPlayer.set(apixCoordinate, apiyCoordinate, apiShip, apiOrient);

    }

    private void doShoot(String parameterString) throws WrongStatusException, OutOfFieldException {

        StringTokenizer st = new StringTokenizer(parameterString);
        int apixCoordinate = Integer.parseInt(st.nextToken());
        int apiyCoordinate = Integer.parseInt(st.nextToken());

        boardOpponent.shoot(apixCoordinate, apiyCoordinate);

    }

    // added (new method prints game rules and parameter options for commands)
    private void doRules() {

        this.outStream.print(
                "\n\nBATTLESHIP RULES\n\n"
                        + "Each player starts with ? small, ? medium and ? big ships.\n"
                        + "In the set up phase you place your ships horizontally or vertically on your board using the 'set' command.\n"
                        + "In the game phase you alternately choose a position on your opponents field you want to 'shoot'.\n"
                        + "Your oppinents ships are invisable to you, only hits are marked on your screen with a ?.\n"
                        + "The Player who hit all parts of all the opponent ships wins.\n\n"
                        + "COMMANDS AND PARAMETERS\n\n"
                        + "open\n"
                        + "connect   Hostname\n"
                        + "set       XCoordinate(A - ?) YCoordinate(1 - ?) Ship(S,M,B) Orientation(H,V)\n"
                        + "shoot     XCoordinate(A - ?) YCoordinate(1 - ?)\n"
                        + "print\n"
                        + "rules\n"
                        + "exit\n"
        );
    }


    private void doExit() throws IOException {
        // shutdown engines which needs to be
        //this.protocolEngine.close();
    }

    private void doOpen() {
        //if (this.alreadyConnected()) return;

        //this.tcpStream = new TCPStream(TicTacToe.DEFAULT_PORT, true, this.playerName);
        //this.tcpStream.setStreamCreationListener(this);
        //this.tcpStream.start();
    }

    private void doConnect(String parameterString) {
       /*
        if (this.alreadyConnected()) return;

        String hostname = null;

        try {
            StringTokenizer st = new StringTokenizer(parameterString);
            hostname = st.nextToken();
        } catch (NoSuchElementException e) {
            System.out.println("no hostname provided - take localhost");
            hostname = "localhost";
        }

        this.tcpStream = new TCPStream(TicTacToe.DEFAULT_PORT, false, this.playerName);
        this.tcpStream.setRemoteEngine(hostname);
        this.tcpStream.setStreamCreationListener(this);
        this.tcpStream.start();

        */
    }

    private void doPrint() throws IOException {
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



}

