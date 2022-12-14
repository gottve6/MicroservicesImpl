package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Invoice;
import com.demo.repository.InvoiceRepository;

@RestController
public class InvoiceController {
	
	@Autowired
	InvoiceRepository repos;
	
	@PostMapping("/invoice/add")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
		
		try {
			Invoice inv = repos.insert(invoice);
			return new ResponseEntity<>(inv, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/invoice/getByName/{shopname}")
	public ResponseEntity<List<Invoice>> findByName(@PathVariable String shopname) {
		try {
			List<Invoice> memberList = repos.findByShopname(shopname);
			if (memberList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/invoice/get")
	public ResponseEntity<List<Invoice>> getAll() {
		try {
			List<Invoice> memberList = repos.findAll();
			if (memberList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/invoice/delete/{id}")
	public String deleteById(@PathVariable String id) {
		try {
			repos.deleteById(Integer.parseInt(id));
			return "Deleted the record for Id: "+id;
		} catch (Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		}
	}
	
	@PutMapping("/invoice/update")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice member) {
		
		try {
			Invoice inv = repos.save(member);
			return new ResponseEntity<>(inv, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/invoice/getByCity/{cityName}")
	public ResponseEntity<List<Invoice>> findByCity(@PathVariable String cityName) {
		try {
			List<Invoice> memberList = repos.findByCity(cityName);
			if (memberList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/invoice/getShopByCity/{cityName}/{shopname}")
	public ResponseEntity<List<Invoice>> findByCityAndShop(@PathVariable String cityName, @PathVariable String shopname) {
		try {
			List<Invoice> memberList = repos.findByCityAndShopname(cityName, shopname);
			if (memberList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
