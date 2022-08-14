package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerApp {
    public TextArea txtArea;
    public TextField txtMassage;
    public Button btnsent;
    DataOutputStream dataOutputStream;
    final int PORT=1234;

    String massage="", reply="";

    public void initialize() {
        CheckClient();

    }

    private void CheckClient(){
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                txtMassage.appendText("Server Start.!");
                Socket localSocket = serverSocket.accept();
                txtMassage.appendText("\nClient Connected..!");
                txtMassage.appendText("\n.............................................\n");

                dataOutputStream=new DataOutputStream(localSocket.getOutputStream());
                DataInputStream dataInputStream=new DataInputStream(localSocket.getInputStream());

                while (!massage.equals("Exit")){
                    massage=dataInputStream.readUTF();
                    txtMassage.appendText("\nClient-01 : " + massage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}