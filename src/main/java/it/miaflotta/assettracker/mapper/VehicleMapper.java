package it.miaflotta.assettracker.mapper;

import it.miaflotta.assettracker.models.dto.vehicle.device.DeviceCategoryDTO;
import it.miaflotta.assettracker.models.dto.vehicle.device.DeviceDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleCategoryDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.Vehicle;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VehicleMapper {
    public static VehicleDTO map(Vehicle vehicle) {
        if (Objects.isNull(vehicle)) {
            return null;
        }

        DeviceDTO device = null;
        if (Objects.nonNull(vehicle.getDevice())) {
            DeviceCategoryDTO deviceCategory = null;
            if (Objects.nonNull(vehicle.getDevice().getCategory())) {
                deviceCategory = new DeviceCategoryDTO(vehicle.getDevice().getCategory().getName(), vehicle.getDevice().getCategory().getType());
            }
            device = new DeviceDTO(vehicle.getDevice().getName(), vehicle.getDevice().getSerialNumber(), vehicle.getDevice().getStatus(), vehicle.getDevice().getOffModeInSec(), deviceCategory);
        }

        VehicleCategoryDTO category= null;
        if (Objects.nonNull(vehicle.getCategory())) {
            category = new VehicleCategoryDTO(null, null, null);
        }

        return new VehicleDTO(vehicle.getId(), vehicle.getName(), vehicle.getPlate(), device, category, null);
    }

    public static List<VehicleDTO> map(List<Vehicle> vehicles) {
        if (CollectionUtils.isEmpty(vehicles)) {
            return new LinkedList<>();
        }

        return vehicles.stream()
                .map(VehicleMapper::map)
                .collect(Collectors.toList());
    }
}
