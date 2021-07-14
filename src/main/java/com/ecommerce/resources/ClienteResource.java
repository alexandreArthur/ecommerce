package com.ecommerce.resources;

import com.ecommerce.domain.Categoria;
import com.ecommerce.domain.Cliente;
import com.ecommerce.dto.CategoriaDTO;
import com.ecommerce.dto.ClienteDTO;
import com.ecommerce.dto.ClienteNewDTO;
import com.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
        Cliente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id){

        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){

        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>>
    findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
             @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
             @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        Page<Cliente> list = service.findByPages(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(ClienteDTO::new);

        return ResponseEntity.ok().body(listDto);

    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody ClienteDTO objDTO){
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        service.put(obj);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
