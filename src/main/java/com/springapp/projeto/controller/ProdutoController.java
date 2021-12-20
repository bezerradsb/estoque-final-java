package com.springapp.projeto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.projeto.model.Produto;
import com.springapp.projeto.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository pr;
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.GET)
	public String form() {
		return "paginas/formProduto";
	}
	
	@PostMapping(value="/cadastrarProduto")
	public String form(Produto produto, @RequestParam("fileImage") MultipartFile multpartiFile) throws IOException {
		
		// cadastrar produto
		String fileName = StringUtils.cleanPath(multpartiFile.getOriginalFilename());
		produto.setLogo(fileName);
		Produto savedProduto = pr.save(produto);
		
		String uploadDir = "./src/main/resources/static/produto-logos/" + savedProduto.getCodigoProduto();
		
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multpartiFile.getInputStream()) {;
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Não salvou o arquivo: " + fileName);
		}
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.GET)
	public ModelAndView alterarProduto(@PathVariable("codigoProduto") long codigoProduto) {
		Produto produto = pr.findByCodigoProduto(codigoProduto);
		ModelAndView mv = new ModelAndView("paginas/alterarProduto");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.POST)
	public String alterarProdutoPost(@PathVariable("codigoProduto") long codigoProduto, Produto produto, @RequestParam("fileImage") MultipartFile multpartiFile) throws IOException {
		String fileName = StringUtils.cleanPath(multpartiFile.getOriginalFilename());
		produto.setLogo(fileName);
		Produto savedProduto = pr.save(produto);
		
		String uploadDir = "./src/main/resources/static/produto-logos/" + savedProduto.getCodigoProduto();
		
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multpartiFile.getInputStream()) {;
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Não salvou o arquivo: " + fileName);
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Produto> produtos = pr.findAll();
		mv.addObject("produtos",produtos);
		return mv;
	}
	
	@RequestMapping(value="/{codigoProduto}", method=RequestMethod.GET)
	public ModelAndView detalhesProduto(@PathVariable("codigoProduto") long codigoProduto) {
		Produto produto = pr.findByCodigoProduto(codigoProduto);
		ModelAndView mv = new ModelAndView("paginas/detalhesProduto");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@RequestMapping("/deletar")
	public String deletarProduto(long codigoProduto) {
		Produto produto = pr.findByCodigoProduto(codigoProduto);
		pr.delete(produto);
		return "redirect:/";
	}
}
