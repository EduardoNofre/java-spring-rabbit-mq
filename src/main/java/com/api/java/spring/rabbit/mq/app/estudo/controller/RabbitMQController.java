package com.api.java.spring.rabbit.mq.app.estudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.java.spring.rabbit.mq.app.estudo.model.RabbitMQModel;
import com.api.java.spring.rabbit.mq.app.estudo.service.RabbitMQService;
import com.api.spring.projections.app.estudo.entity.EmpresaEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Validated
@RequestMapping("/api//")
@Tag(name = "Empresa Controller", description = "RabbitMQ API")
public class RabbitMQController {

	@Autowired
	private RabbitMQService rabbitMQService;
	
	@Operation(summary = "Envia mensgaem para a fila", description = "Envia mensgaem para a fila", tags = {"Send msg queue" })
	@ApiResponses(value = { @ApiResponse(responseCode = "", description = ""+
			" <br />responseCode = 200, RabbitMQ encontrado com sucesso" + 
			" <br />responseCode = 400, Erro processar a requisição" + 
			" <br />responseCode = 401, Não autorizado." + 
			" <br />responseCode = 404, RabbitMQ não encontrado." +
			" <br />responseCode = 500, Erro interno sem causa mapeada." +
			" <br />responseCode = 504, Gateway Time-Out." 
			)})
	@PostMapping(value = "envia/msg/{msg}}", produces = { "application/json", "application/xml" })
	public ResponseEntity<String>  sendMsg (String msg) {

		return new ResponseEntity<String>(rabbitMQService.buscaIdEmpresa(id), HttpStatus.OK);
	}

	@Operation(summary = "Busca mensgaem na fila", description = "Busca mensgaem na fila", tags = {"get msg queue" })
	@ApiResponses(value = { @ApiResponse(responseCode = "", description = ""+
			" <br />responseCode = 200, RabbitMQ encontrado com sucesso" + 
			" <br />responseCode = 400, Erro processar a requisição" + 
			" <br />responseCode = 401, Não autorizado." + 
			" <br />responseCode = 404, RabbitMQ não encontrado." +
			" <br />responseCode = 500, Erro interno sem causa mapeada." +
			" <br />responseCode = 504, Gateway Time-Out." 
			)})
	@GetMapping(value = "id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<String>(@PathVariable String msg) {
		return new SomeData();
	}
	
	@Operation(summary = "Envia JSON para a fila", description = "Envia JSON para a fila", tags = {"Send JSON queue" })
	@ApiResponses(value = { @ApiResponse(responseCode = "", description = ""+
			" <br />responseCode = 200, RabbitMQ encontrado com sucesso" + 
			" <br />responseCode = 400, Erro processar a requisição" + 
			" <br />responseCode = 401, Não autorizado." + 
			" <br />responseCode = 404, RabbitMQ não encontrado." +
			" <br />responseCode = 500, Erro interno sem causa mapeada." +
			" <br />responseCode = 504, Gateway Time-Out." 
			)})
	@PostMapping(value = "envia/json/nome{nome}/nomeFantasia{nomeFantasia}/cnpj{cnpj}", produces = { "application/json", "application/xml" })
	public ResponseEntity<RabbitMQModel> postMethodName(@RequestBody RabbitMQModel rabbitMQModel) {
		// TODO: process POST request

		return rabbitMQModel;
	}
	
	@Operation(summary = "Busca JSON na fila", description = "Busca JSON na fila", tags = {"get JSON queue" })
	@ApiResponses(value = { @ApiResponse(responseCode = "", description = ""+
			" <br />responseCode = 200, RabbitMQ encontrado com sucesso" + 
			" <br />responseCode = 400, Erro processar a requisição" + 
			" <br />responseCode = 401, Não autorizado." + 
			" <br />responseCode = 404, RabbitMQ não encontrado." +
			" <br />responseCode = 500, Erro interno sem causa mapeada." +
			" <br />responseCode = 504, Gateway Time-Out." 
			)})
	@GetMapping(value = "id/{id}", produces = { "application/json", "application/xml" })
	public SomeData getMethodName(@RequestParam String param) {
		return new SomeData();
		
	}
	
	

}
