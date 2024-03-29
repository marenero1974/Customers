package it.customers.h2db.springboot.repositry;

import it.customers.h2db.springboot.models.Device;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {

  Integer deleteDeviceByUuid(UUID uuid);

  Device findDeviceByUuid(UUID uuid);

}
