package final_project;

import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

import final_project.Board.Cell;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class controller{
	@FXML private Button startbt;
	@FXML private Button rulebt;
	@FXML private Button settingbt;
	@FXML private Button backtomenubt;
	@FXML private Button settingback;
	@FXML private ImageView ruleimage;
	@FXML private ImageView ruleimage1;
	@FXML private ImageView ship;
	@FXML private Scene scene;
	@FXML private Stage stage;
	@FXML private Parent root;
	@FXML private Button backtost;
	@FXML private Button next;
	@FXML private Button returnbt;
	@FXML public RadioButton ei;
	@FXML public RadioButton te;
	@FXML public RadioButton tw;
	@FXML public RadioButton LB;
	@FXML public RadioButton db;
	@FXML public RadioButton gb;
	@FXML public RadioButton black;
	@FXML public RadioButton darkblue;
	@FXML public RadioButton darkyellow;
	@FXML public Label num1;
	@FXML public Label num2;
	@FXML public static Color ocean=Color.LIGHTBLUE;
	@FXML public static Color shipColor=Color.BLACK;
	public ArrayList<Cell> eshot = new ArrayList<Cell>();
	ArrayList<Integer> tmp;
	public int x=0,y=0,c=0,d=0,w=-1;
	public static int field=8;
	private boolean running = false;
    private Board enemyBoard, playerBoard;
    public static int shipsToPlace=4,r=4;
    private boolean enemyTurn = false;
    private Random random = new Random();
	//start pane
    public void shipmove() {
    	PathTransition pathTransition = new PathTransition(Duration.millis(8000),new Line(-238,100,950,100),ship);
	    pathTransition.setCycleCount(Timeline.INDEFINITE);
	    pathTransition.play();
    }
	public void backtomenu() {	
		ruleimage1.setVisible(false);
		ruleimage.setVisible(false);
		backtomenubt.setVisible(false);
		next.setVisible(false);
		returnbt.setVisible(false);
		shipmove();
	}
	public void nextrule() {	
		ruleimage1.setVisible(true);
		ruleimage.setVisible(false);
		backtomenubt.setVisible(true);
		next.setVisible(false);
		returnbt.setVisible(true);
	}
	public void torule1() {	
		ruleimage1.setVisible(false);
		ruleimage.setVisible(true);
		backtomenubt.setVisible(true);
		next.setVisible(true);
		returnbt.setVisible(false);
	}
	public void torule() {
		ruleimage1.setVisible(false);
		ruleimage.setVisible(true);
		backtomenubt.setVisible(true);
		next.setVisible(true);
		returnbt.setVisible(false);
	}
	public void setfield() {
		num1.setText("8");
		num2.setText("8");
		if(ei.isSelected()) {
			num1.setText("8");
			num2.setText("8");
			field=8;
			shipsToPlace=4;
			r=4;
		}else if(te.isSelected()) {
			num1.setText("10");
			num2.setText("10");
			field=10;
			shipsToPlace=5;
			r=5;
		}else if(tw.isSelected()) {
			num1.setText("12");
			num2.setText("12");
			field=12;
			shipsToPlace=6;
			r=6;
		}
	}
	public void setcolor() {
		if(LB.isSelected()) {
			ocean=Color.LIGHTBLUE;
		}else if(db.isSelected()) {
			Color paint2 = new Color(0.7098, 0.1059, 0.7098, 1.0);
			ocean = paint2;
		}else if(gb.isSelected()) {
			Color paint3 = new Color(0.2078, 0.9608, 0.2078, 1.0);
			ocean = paint3;
		}
	}
	public void setshipcolor() {
		if(black.isSelected()) {
			shipColor=Color.BLACK;
		}else if(darkblue.isSelected()) {
			Color paint = new Color(0.1059, 0.2588, 0.698, 1.0);
			shipColor = paint;
		}else if(darkyellow.isSelected()) {
			Color paint4 = new Color(0.7333, 0.8667, 0.1882, 1.0);
			shipColor = paint4;
		}
	}
	public void tosettings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	//setting pane
	public void tostart(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	//start game pane
	public void startgame(ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(createContent());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.show();
	}
	Label Es= new Label(Integer.toString(r));
	Label Ys= new Label(Integer.toString(r));
	Label remain = new Label("Remain");	
	public Parent createContent() {
		ArrayList<Label> tag = new ArrayList<Label>();
		VBox T = new VBox(30);
		T.getChildren().add(remain);
		remain.setFont(new Font("Tw Cen MT Condensed",30));
		T.setAlignment(Pos.CENTER_LEFT);
		for(int i=1;i<r+1;i++) {
			Label x = new Label(Integer.toString(i));
			x.setFont(new Font("Arial",40));
			T.getChildren().add(x);
			tag.add(x);
		}
		Es.setFont(new Font("Arial", 40));
		Ys.setFont(new Font("Arial", 40));
		Label E = new Label("Enemy's ships");
		E.setFont(new Font("Tw Cen MT Condensed", 45));
		E.setLayoutX(10);E.setLayoutY(10);
		Label Y = new Label("Your ships");
		Y. setFont(new Font("Tw Cen MT Condensed", 45));
		Y.setLayoutX(500);Y.setLayoutY(10);		
		BorderPane root = new BorderPane();
        root.setPrefSize(1000, 600);
        enemyBoard = new Board(true, event -> {
            if (!running)
                return;
            Cell cell = (Cell) event.getSource();
            String t=String.valueOf(enemyBoard.ships);
            Es.setText(t);
            if (cell.wasShot) {
            	String d=String.valueOf(enemyBoard.ships);
            	Es.setText(d);
                return;}
            int o=enemyBoard.ships;
            enemyTurn = !cell.shoot();
            String t1=String.valueOf(enemyBoard.ships);
            Es.setText(t1);
            if(enemyBoard.ships!=o) {
            	for(int i=0;i<r;i++) {
            		if(cell.calltype()==i+1) {
            			tag.get(i).setVisible(false);
            		}
            	}
            }
            if (enemyBoard.ships == 0) {
            	try {
					Parent root1 = FXMLLoader.load(getClass().getResource("result.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root1);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (enemyTurn) {            	
            	enemyMove();
            }              
        });
        playerBoard = new Board(false, event -> {
            if (running) 
                return;
            Cell cell = (Cell) event.getSource();    
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });
        HBox L = new HBox(150, Y, E );
        L.setAlignment(Pos.CENTER);
        HBox S = new HBox(800, Ys, Es );
        S.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(100, playerBoard, enemyBoard);
        hbox.setAlignment(Pos.CENTER);
        root.setRight(T);
        root.setTop(L);
        root.setCenter(hbox);
        root.setBottom(S);
        return root;
	}
	public void enemyMove() {
		while (enemyTurn) {
			if(eshot.size()==0) {
				x= random.nextInt(field);
            	y= random.nextInt(field);
			}else {			
				tmp=eshot.get(eshot.size()-1).getxy();
				if(c==4)c=0;
				if(c==0) {
					x=tmp.get(0)+1;
					y=tmp.get(1);
				}else if(c==1) {
					x=tmp.get(0);
					y=tmp.get(1)+1;
				}else if(c==2) {
					x=tmp.get(0)-1;
					y=tmp.get(1);
				}else if(c==3) {
					x=tmp.get(0);
					y=tmp.get(1)-1;	
				}
			}
			if(x<=-1||x>=field||y<=-1||y>=field) {
				if(x<=-1) {
					x++;
				}else if(x>=field) {
					x--;
				}else if(y<=-1) {
					y++;
				}else if(y>=field) {
					y--;
				}
				if(eshot.size()>1) {
            		if(c==0) {
    					c=2;
    				}else if(c==1) {
    					c=3;
    				}else if(c==2) {
    					c=0;
    				}else if(c==3) {
    					c=1;
    				}
            	}else {
            		c++;
            		if(c>4) {
            			c=0;
            		}
            	}
            	continue;
            }
            Cell cell = playerBoard.getCell(x, y);
            int t=playerBoard.ships;
            String p=String.valueOf(playerBoard.ships);
        	Ys.setText(p);      	
            if (cell.wasShot) { 
            	if(eshot.size()>0) {
            		for(int i =0;i<eshot.size();i++) {
            			if(cell==eshot.get(i)) {
            				tmp=eshot.get(0).getxy();
            				if(c==0) {
            					x=tmp.get(0)+1;
            					y=tmp.get(1);
            				}else if(c==1) {
            					x=tmp.get(0);
            					y=tmp.get(1)+1;
            				}else if(c==2) {
            					x=tmp.get(0)-1;
            					y=tmp.get(1);
            				}else if(c==3) {
            					x=tmp.get(0);
            					y=tmp.get(1)-1;	
            				}  				
            				cell = playerBoard.getCell(x, y);
            				if (cell.wasShot) { 
            					c++;
            					if(c==0) {
                					x=tmp.get(0)+1;
                					y=tmp.get(1);
                				}else if(c==1) {
                					x=tmp.get(0);
                					y=tmp.get(1)+1;
                				}else if(c==2) {
                					x=tmp.get(0)-1;
                					y=tmp.get(1);
                				}else if(c==3) {
                					x=tmp.get(0);
                					y=tmp.get(1)-1;	
                				}            					
            					cell = playerBoard.getCell(tmp.get(0),tmp.get(1));
            					if(eshot.size()>1) {
            	            		if(c==0) {
            	    					c=2;
            	    				}else if(c==1) {
            	    					c=3;
            	    				}else if(c==2) {
            	    					c=0;
            	    				}else if(c==3) {
            	    					c=1;
            	    				}
            	            	}else {
            	            		c++;
            	            		if(c>4) {
            	            			c=0;
            	            		}
            	            	}
            				}
            				d=1;
            				break;
            			}
            		}
            		if(d==1) {
            			d=0;
            		}else {
            			c++;
                    	if(c>4) {
                			c=0;
                		}
            			continue;
            		}
            	}else {
            		continue;
            	}
            }
            if(x<=-1||x>=field||y<=-1||y>=field) {
            	c++;
            	if(c>4) {
        			c=0;
        		}
            	continue;
            }
            enemyTurn = cell.shoot();
            if(enemyTurn==true) {
            	if(eshot.size()!=0) {
            		w=c;
            	}
            	eshot.add(cell);
            }else {
            	if(eshot.size()>1) {
            		if(c==0) {
    					c=2;
    				}else if(c==1) {
    					c=3;
    				}else if(c==2) {
    					c=0;
    				}else if(c==3) {
    					c=1;
    				}
            	}else {
            		c++;
            		if(c>4) {
            			c=0;
            		}
            	}
            }
            if(playerBoard.ships!=t) {
            	eshot.clear();
            }
            if (playerBoard.ships == 0) {
            	try {
					Parent root2 = FXMLLoader.load(getClass().getResource("result2.fxml"));
					scene = new Scene(root2);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
	}
	public void startGame() {
		if(shipsToPlace==0) {
			shipsToPlace=field/2;
			r=shipsToPlace;
		}
		int type = r;
        while (type > 0) {
            int x = random.nextInt(field);
            int y = random.nextInt(field);
            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }
        running = true;
	}
	public static int getf() {
		return field;
	}
	public static Color getc() {
		return ocean;
	}
	public static Color getsc() {
		return shipColor;
	}
}
