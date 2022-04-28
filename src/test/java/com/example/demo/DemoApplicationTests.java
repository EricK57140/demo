package com.example.demo;

import com.example.demo.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	private void inisialisation() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	void utilisateurListeRole_initialize() {

		Utilisateur utilisateur = new Utilisateur();

		assertDoesNotThrow(() -> utilisateur.getListeRole().size());


	}

	@Test
	@WithMockUser(username = "doe", roles = {"USER"})
	void simpleUserGetListeMateriel_response200Ok() throws Exception {
		mvc.perform(get("/Liste-materiel")).andExpect(status().isOk());

	}
	@Test
	@WithMockUser(username = "doe", roles = {"USER"})
	void simpleUserGetListeMarque_response403Forbidden() throws Exception {
		mvc.perform(get("/admin/Liste-marque")).andExpect(status().isForbidden());

	}
	@Test
	@WithMockUser(username = "doe", roles = {"ADMIN"})
	void simpleUserGetListeMarque_response200Ok() throws Exception {
		mvc.perform(get("/admin/Liste-marque")).andExpect(status().isOk());

	}
	@Test
	@WithMockUser(username = "doe", roles = {"USER"})
	void simpleUserGetLaterielId1_materielComporteECRANDELL001() throws Exception {
		mvc.perform(get("/materiel/1"))
				.andExpect(jsonPath("$.code")
						.value("ECRANDELL001"));

	}

	@Test
	@WithMockUser(username = "doe", roles = {"USER"})
	void simpleUserGetListeReservation_utilisateurSansMdp() throws Exception {
		mvc.perform(get("/Liste-reservation"))
				.andExpect(jsonPath("$[0].emprunteur.mdp").doesNotExist());


	}

}
