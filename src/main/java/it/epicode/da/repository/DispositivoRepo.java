package it.epicode.da.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.da.model.Dispositivo;


public interface DispositivoRepo extends PagingAndSortingRepository<Dispositivo, Long>,CrudRepository<Dispositivo, Long> {

}
