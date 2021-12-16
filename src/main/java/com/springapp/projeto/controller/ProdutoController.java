package com.springapp.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.POST)
	public String form(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os Campos!");
			return "redirect:/cadastrarProduto";
		}
		// cadastrar produto
		pr.save(produto);
		attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
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
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.GET)
	public ModelAndView alterarProduto(@PathVariable("codigoProduto") long codigoProduto) {
		Produto produto = pr.findByCodigoProduto(codigoProduto);
		ModelAndView mv = new ModelAndView("paginas/alterarProduto");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.POST)
	public String alterarProdutoPost(@PathVariable("codigoProduto") long codigoProduto, Produto produto){
		pr.save(produto);
		return "redirect:/";
		}
	
	@RequestMapping("/deletar")
	public String deletarProduto(long codigoProduto) {
		Produto produto = pr.findByCodigoProduto(codigoProduto);
		pr.delete(produto);
		return "redirect:/";
	}
}
