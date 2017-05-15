package sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.model.Device;

import java.util.List;

/**
 * Created by luiz on 15/05/17.
 */
public class DeviceList {

    List<Device> devices;

    public DeviceList(@JsonProperty("devices") List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

}
