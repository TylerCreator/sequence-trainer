package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Admin on 21.11.2016.
 */
public class TrainControl implements Initializable{

    @FXML
    AnchorPane train_layout;
    @FXML
    TreeView<String> tv_tree;
    @FXML
    TextField tf_seq_tr;
    @FXML
    TextField tf_add_tr;
    @FXML
    Button btn_1_tr;
    @FXML
    Button btn_2_tr;
    @FXML
    Button btn_3_tr;
    @FXML
    Button btn_4_tr;
    @FXML
    Button btn_5_tr;
    @FXML
    Button btn_6_tr;
    @FXML
    Button btn_7_tr;
    @FXML
    Button btn_8_tr;
    @FXML
    Button btn_9_tr;
    @FXML
    Button btn_10_tr;
    @FXML
    Button btn_11_tr;
    @FXML
    Button btn_12_tr;
    @FXML
    Button btn_start_tr;
    @FXML
    Button btn_instr;
    @FXML
    Button btn_del;
/*    @FXML
    Button btn_prove;
*/
    @FXML
    Button btn_ck;

    Functions sequence;

    Vector<Boolean> axioma = new Vector<Boolean>();

    String text = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tv_tree.setVisible(false);
        tf_add_tr.setVisible(false);
        btn_1_tr.setVisible(false);
        btn_2_tr.setVisible(false);
        btn_2_tr.setVisible(false);
        btn_3_tr.setVisible(false);
        btn_4_tr.setVisible(false);
        btn_5_tr.setVisible(false);
        btn_6_tr.setVisible(false);
        btn_7_tr.setVisible(false);
        btn_8_tr.setVisible(false);
        btn_9_tr.setVisible(false);
        btn_10_tr.setVisible(false);
        btn_11_tr.setVisible(false);
        btn_12_tr.setVisible(false);
        btn_start_tr.setVisible(true);
        btn_del.setVisible(false);
        btn_ck.setVisible(false);
        btn_instr.setVisible(false);
//        btn_prove.setVisible(false);

/*считывание инструкции из файла
        File file = new File("src/Info.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNext()) {
            // считываем строку (имя)
            String line = in.nextLine();
            text=text+line+"\n";

        }
*/
        btn_instr.setOnAction(e -> AlertInfo.display("Инструкция",text));
    }

    public String out(String string){
        string = string.replace('+','├');
        string = string.replace('=','→');
        return string;
    }

    public String in(String string){
        string = string.replace('├','+');
        string = string.replace('→','=');
        return string;
    }

    public void readSequence(ActionEvent actionEvent) throws IOException {
        String s=tf_seq_tr.getText();
        if(Functions.SequenceCorrect(s)){

            makeTree(out(s));
            tf_seq_tr.setEditable(false);
            tf_seq_tr.setText("+ знак секвенции ├                                    "+
                    "- отрицание, не                                    "+
                    "= знак следования →");
            tv_tree.setVisible(true);
            tf_add_tr.setVisible(true);
            btn_1_tr.setVisible(true);
            btn_2_tr.setVisible(true);
            btn_2_tr.setVisible(true);
            btn_3_tr.setVisible(true);
            btn_4_tr.setVisible(true);
            btn_5_tr.setVisible(true);
            btn_6_tr.setVisible(true);
            btn_7_tr.setVisible(true);
            btn_8_tr.setVisible(true);
            btn_9_tr.setVisible(true);
            btn_10_tr.setVisible(true);
            btn_11_tr.setVisible(true);
            btn_12_tr.setVisible(true);
            btn_del.setVisible(true);
            btn_ck.setVisible(true);
            btn_instr.setVisible(true);
            btn_start_tr.setVisible(false);
//            btn_prove.setVisible(true);
        }
        else{
            AlertLog.display("Ввод","Секвенция введена некорректно");
        }

        tf_add_tr.setText(null);
    }

    public void first(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_1(1, s) != s&&Useaccess()) {

            makeBranch(out(Functions.Sequence_1(1, s)),getItem());
            makeBranch(out(Functions.Sequence_1(2,s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        }
    }

    public void second(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_2_3(2,s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_2_3(3, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }



    public void third(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_2_3(3,s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_2_3(2, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void fourth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_4_5(4,s) != s&&Useaccess()) {
            makeBranch(out(Functions.Sequence_4_5(4, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void fifth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_4_5(5,s) != s&&Useaccess()) {
            makeBranch(out(Functions.Sequence_4_5(5, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void sixth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_6(6,s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_6(1, s)),getItem());
            makeBranch(out(Functions.Sequence_6(2, s)),getItem());
            makeBranch(out(Functions.Sequence_6(3, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void seventh(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_7(s) != s&&Useaccess()) {
            makeBranch(out(Functions.Sequence_7(s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void eighth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_8(8,s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_8(1, s)),getItem());
            makeBranch(out(Functions.Sequence_8(2, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void ninth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_9(s) != s&&Useaccess()) {
            makeBranch(out(Functions.Sequence_9(s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void tenth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_10(10,s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_10(1, s)),getItem());
            makeBranch(out(Functions.Sequence_10(2, s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void eleventh(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        Functions.setBufString(tf_add_tr.getText());
        if (Functions.Sequence_11(s) != s&& Functions.getBufString()!=null&&Useaccess()) {
            makeBranch(out(Functions.Sequence_11(s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

    public void twelfth(ActionEvent actionEvent) throws IOException {
        String s = in(getItem().getValue());
        if (Functions.Sequence_12(s) != s&&Useaccess()) {
            makeBranch(out(Functions.Sequence_12(s)),getItem());
            tf_add_tr.setText(null);
            Functions.setBufString(null);
        } else {
            //введите параметры и проверьте секвенцию на корректность

        }
    }

//в меню
    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/menu.fxml"));
        Scene sceneMenu = new Scene(root, 720, 480);
        Stage goSceneMenu = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        goSceneMenu.setScene(sceneMenu);
        goSceneMenu.setTitle("Секвенция");
    }

    //работа с деревьями
    //создание дерева
    public void makeTree(String s){
        TreeItem<String> main = new TreeItem<>(s);
        main.setExpanded(true);
        tv_tree.setRoot(main);
    }

    public void makeBranch(String title, TreeItem<String> parent){
        TreeItem<String>  item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
    }

    public TreeItem<String> getItem(){
        TreeItem<String>  item = tv_tree.getSelectionModel().getSelectedItem();
        return item;
    }

    public void delItem(ActionEvent actionEvent){
        ObservableList<TreeItem<String>> c =  tv_tree.getSelectionModel().getSelectedItem().getChildren();
        tv_tree.getSelectionModel().getSelectedItem().getChildren().removeAll(c);
    }

    public void Check(ActionEvent actionEvent) throws Exception{
        find(tv_tree.getSelectionModel().getSelectedItem());
        int i=0;
        int count=0;
        while(i<axioma.size()){
            if(axioma.get(i))
                count++;
            i++;
        }
        if (count==axioma.size()){
            AlertLog.display("Результат","Поздравляем! У вас получилось!");
        }
        else{
            AlertLog.display("Результат","К сожалению, вы допустили ошибку");
        }
        axioma.clear();
    }

/*    public void provable(ActionEvent actionEvent){
        String s = in(getItem().getValue());
        if (Functions.SequenceDoc(s)){
            AlertLog.display("Доказуемость","Доказуема");
        }
        else{
            AlertLog.display("Доказуемость","Не доказуема");
        }
    }
*/
    public void find(TreeItem item) throws Exception{
        int size =item.getChildren().size();
        if (size!=0){
            for (int i=0;i<size;i++){
                find((TreeItem) item.getChildren().get(i));
            }
        }
        else{
            axioma.add(Functions.SequenceAxioma(in((String)item.getValue())));
        }
    }



    //если уже есть потомки в дереве, то нельзя применять функции, поэтому возвращаем false, если не пусто
    public boolean Useaccess(){
        return tv_tree.getSelectionModel().getSelectedItem().getChildren().size() == 0;
    }



}
