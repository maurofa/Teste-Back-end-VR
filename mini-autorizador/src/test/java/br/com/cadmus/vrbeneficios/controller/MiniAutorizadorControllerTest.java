package br.com.cadmus.vrbeneficios.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadmus.vrbeneficios.cmd.MantemCartaoCmd;
import br.com.cadmus.vrbeneficios.to.CartaoTO;

@Disabled
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = MiniAutorizadorController.class)
class MiniAutorizadorControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private MantemCartaoCmd mantemCartaoCmd;
	private CartaoTO cartaoTO;
	
	@BeforeEach
	void inicio() {
		cartaoTO = new CartaoTO("1111222233334444", "123123");
	}
	
	@Test
	void test() throws Exception {
		when(mantemCartaoCmd.criaCartao(cartaoTO)).thenReturn(cartaoTO);
		
		mvc.perform(post("/cartoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cartaoTO)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.numeroCartao", Matchers.equalTo(cartaoTO.numeroCartao())));
	}

}
