package com.io.java.events.managers.infrastructure.repository;

import com.io.java.events.managers.infrastructure.entity.EventEntity;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, String> {

    Optional<EventEntity> findByName(String name);
    Optional<List<EventEntity>> findByDate(LocalDateTime date);

    private void ifEventExistsByNameThrow(String name){
        if(this.findByName(name).isPresent()){
            throw new DuplicateKeyException("Já existe um evento com esse nome no banco de dados, tente variar o nome para evitar dados duplicados.");
        }
    }

    default EventEntity findByNameOrElseThrow(String name){
        return this.findByName(name).orElseThrow(() -> new NullPointerException("Evento não encontrado com esse nome: " + name));
    }

    default List<EventEntity> findByDateOrElseThrow(LocalDateTime date){
        return this.findByDate(date).orElseThrow(() -> new NullPointerException("Evento não encontrado com essa data: " + date));
    }

    @Transactional
    default void saveOrElseThrow(EventEntity eventEntity){
       this.ifEventExistsByNameThrow(eventEntity.getName());
       this.save(eventEntity);
    }

    default EventEntity findByIdOrElseThrow(String id){
        return this.findById(id).orElseThrow(() -> new NullPointerException("Evento não encontrado com o id: " + id));
    }

    @Transactional
    default void  updateEventOrElseThrow(EventEntity eventEntity){
        EventEntity entity = this.findByIdOrElseThrow(eventEntity.getId());
        entity.setDate(eventEntity.getDate());
        entity.setName(eventEntity.getName());
        entity.setStatus(eventEntity.getStatus());
        entity.setDescription(eventEntity.getDescription());
        this.save(entity);
    }

}
