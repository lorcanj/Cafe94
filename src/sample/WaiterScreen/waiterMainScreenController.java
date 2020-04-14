package sample.WaiterScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DBManager;
import sample.Report;
import sample.item;
import sample.user;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class waiterMainScreenController {
    @FXML private ComboBox<item> favoriteList;
    @FXML private ComboBox<item> starterList;
    @FXML private ComboBox<item> mainList;
    @FXML private ComboBox<item> sideList;
    @FXML private ComboBox<item> dessertList;
    @FXML private ComboBox<item> drinkList;
    @FXML private TableView<item> finalOrderView;
    @FXML private TableColumn<item, String> itemName;
    @FXML private TableColumn<item, Double> itemPrice;
    @FXML private Label menuResultPrice;
    private double totalCost;

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ObservableList<item> favoriteObservableList = FXCollections.observableArrayList();
    public ObservableList<item> starterObservableList = FXCollections.observableArrayList();
    public ObservableList<item> mainObservableList = FXCollections.observableArrayList();
    public ObservableList<item> sideObservableList = FXCollections.observableArrayList();
    public ObservableList<item> dessertObservableList = FXCollections.observableArrayList();
    public ObservableList<item> drinkObservableList = FXCollections.observableArrayList();
    public ObservableList<item> resultList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        String query = "SELECT * FROM menu";
//        String sql = "CREATE TABLE IF NOT EXISTS menu(\n"
//                + "	id integer PRIMARY KEY,\n"
//                + "	name varchar(255) NOT NULL,\n"
//                + "	price real NOT NULL, \n"
//                + " type varchar(255) NOT NULL \n"
//                + ");";
//        String addMenuQuery = "INSERT INTO menu(name,price,type) "
//                + "VALUES"
//                + "('Favorite1','5.21','favorite'), \n"
//                + "('Favorite2','3.21','favorite'), \n"
//                + "('Onion Rings','5.21','starter'), \n"
//                + "('Garlic Bread','4.23','starter'), \n"
//                + "('Chicken Burger','7.21','main'), \n"
//                + "('Newyork Steak','9.00','main'), \n"
//                + "('French Fries','2.21','side'), \n"
//                + "('Spicy Fried','3.00','side'), \n"
//                + "('Vanilla Cheesecake','4.12','dessert'), \n"
//                + "('Fosters','6.00','drink'), \n"
//                + "('Sprite','2.20','drink');";

        connection = DBManager.DBConnection();
        try{
//            PreparedStatement menuCheck= connection.prepareStatement(sql);
//            menuCheck.executeUpdate();
//
//            PreparedStatement setMenu = connection.prepareStatement(addMenuQuery);
//            setMenu.executeUpdate();

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            fillMenuLists(rs);
            favoriteList.setItems(favoriteObservableList);
            starterList.setItems(starterObservableList);
            mainList.setItems(mainObservableList);
            sideList.setItems(sideObservableList);
            dessertList.setItems(dessertObservableList);
            drinkList.setItems(drinkObservableList);
        }
        catch(Exception e ){
            System.out.println("Reason of the problem is: " + e);
        }

    }

    public void fillMenuLists(ResultSet rs) throws SQLException {
        while(rs.next()){
            item temp = new item();
            temp.setId(rs.getInt("id"));
            temp.setItemName(rs.getString("name"));
            temp.setPrice(rs.getDouble("price") );
            String type = rs.getString("type");
            temp.setType(type);
            if(type.equals("favorite")) favoriteObservableList.add(temp);
            else if(type.equals("starter")) starterObservableList.add(temp);
            else if(type.equals("main")) mainObservableList.add(temp);
            else if(type.equals("side")) sideObservableList.add(temp);
            else if(type.equals("dessert")) dessertObservableList.add(temp);
            else if(type.equals("drink")) drinkObservableList.add(temp);
        }
    }

    public void onAddFavoriteButtonPressed(ActionEvent event){
        try{
            if(!favoriteList.getSelectionModel().isEmpty()) {
                item temp = favoriteList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onAddStarterButtonPressed(ActionEvent event){
        try{
            if(!starterList.getSelectionModel().isEmpty()) {
                item temp = starterList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onAddMainButtonPressed(ActionEvent event){
        try{
            if(!mainList.getSelectionModel().isEmpty()) {
                item temp = mainList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onAddSideButtonPressed(ActionEvent event){
        try{
            if(!sideList.getSelectionModel().isEmpty()) {
                item temp = sideList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onAddDessertButtonPressed(ActionEvent event){
        try{
            if(!dessertList.getSelectionModel().isEmpty()) {
                item temp = dessertList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onAddDrinkButtonPressed(ActionEvent event){
        try{
            if(!drinkList.getSelectionModel().isEmpty()){
                item temp = drinkList.getSelectionModel().getSelectedItem();
                resultList.add(temp);
                itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
                itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
                finalOrderView.setItems(resultList);
                totalCost += temp.getPrice();
                menuResultPrice.setText(String.valueOf(totalCost));
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onFinalizeOrderButtonPressed(ActionEvent event){
        try {
            itemName.setCellValueFactory(new PropertyValueFactory<item, String>("itemName"));
            itemPrice.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
            finalOrderView.setItems(resultList);
            resultList.clear();
            totalCost += 0.0;
            menuResultPrice.setText("0.00");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }






}