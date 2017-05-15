package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.dto.DeviceList;
import sample.firebase.FirebaseConfig;
import sample.firebase.FirebaseSender;
import sample.model.Device;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private FirebaseConfig firebaseConfig = new FirebaseConfig(
            "https://fcm.googleapis.com/fcm/send",
            "your server key");


    @FXML
    private
    TextField content;

    @FXML
    private
    Label status;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {

        List<Device> devices = loadDevices();

        if (devices != null) {

            for (Device device : devices) {
                sendToDevice(device.getToken(), content.getText());
            }
        }

    }

    private void sendToDevice(String device, String message) {

        status.setText("sending message...");

        try {
            FirebaseSender firebaseSender = new FirebaseSender(firebaseConfig);
            if (firebaseSender.envia(device, message)) {
                status.setText("Message sent.");
            } else {
                status.setText("Sending failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Device> loadDevices() {

        DeviceList deviceList = null;

        File file = new File(getClass().getResource("/devices.json").getFile());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            deviceList = objectMapper.readValue(file, DeviceList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return deviceList.getDevices();
    }
}
