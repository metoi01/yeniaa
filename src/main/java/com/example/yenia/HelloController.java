package com.example.yenia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class HelloController {
    //Variables

    //Gui Variables
    @FXML
    private Button usernameButton,mybasketButton,chiefsButton,myfridgeButton;
    @FXML
    private SplitPane mybasketPane,usernamePane,chiefsPane,myfridgePane;
    @FXML
    private Pane loginPane,registerPane,mainPane;
    @FXML
    private TextField loginUsername,loginPassword;
    @FXML
    private Text loginError;

    //AL Variables
    private DataBase db=new DataBase();
    private int currentPage=3;
    //Methods

    //Button Methods
    @FXML
    protected void usernameButtonClick()
    {
        changePage(0);
    }
    @FXML
    protected void myfridgeButtonClick()
    {
        changePage(4);
    }
    @FXML
    protected void chiefsButtonClick() {changePage(2);}
    @FXML
    protected void mybasketButtonClick()
    {
        changePage(1);
    }
    @FXML
    protected void createAccButtonClick()
    {
        loginPane.setVisible(false);
        loginPane.setDisable(true);
        registerPane.setVisible(true);
        registerPane.setDisable(false);
    }
    @FXML
    protected void regLoginButtonClick()
    {
        loginPane.setVisible(true);
        loginPane.setDisable(false);
        registerPane.setVisible(false);
        registerPane.setDisable(true);
    }
    @FXML
    protected void loginButtonClick()
    {
        if(db.doesUserExists(loginUsername.getText()))
        {
            if(db.getPasswordOf(loginUsername.getText()).equals(loginPassword.getText()))
            {
                UserMemory.storeUser(loginUsername.getText(),loginPassword.getText());
                loginPane.setVisible(false);
                loginPane.setDisable(true);
                mainPane.setVisible(true);
                mainPane.setDisable(false);
            }
            else
            {
                loginError.setText("Password is incorrect.");
            }
        }
        else
        {
            loginError.setText("User cannot be found.");
        }
    }

    //AL Methods
    public void changePage(int i)
    {
        getCurrentPage().setVisible(false);
        getCurrentPage().setDisable(true);
        currentPage=i;
        getCurrentPage().setVisible(true);
        getCurrentPage().setDisable(false);
    }
    public SplitPane getCurrentPage()
    {
        if(currentPage==4)return myfridgePane;
        if(currentPage==1)return mybasketPane;
        if(currentPage==2)return chiefsPane;
        return usernamePane;
    }

}