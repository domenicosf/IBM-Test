package com.ibm.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ibm.dao.UserRepository;
import com.ibm.http.UserXML;
import com.ibm.model.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Essa classe vai expor os métodos para serem acessados via http
 * 
 * @Path - caminho para a chamada da classe que vai representar o nosso serviço
 */
@Api(value="/users", description="Manage User Entity")
@Path("/users")
public class MyResource {

	private final UserRepository repository = new UserRepository();

	/*
	 * Consumes - determina o formato dos dados que iremos postar Produces -
	 * determina o formato dos dados que iremos retornar
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/create")
	@ApiOperation(value="Create user in the database",
	notes="Create user in the database")
	@ApiResponses(value= {@ApiResponse(code=200, message="User armazenado com sucesso"),
			@ApiResponse(code=400, message="Requisição Inválida!")})
	public Response create(@ApiParam(value = "Classe que armazena todos os dados necessários a entidade User", required = true) UserXML userXML) {
		User user = new User();

		try {
			user.setName(userXML.getName());
			user.setEmail(userXML.getEmail());
			user.setStatus(userXML.isStatus());

			repository.save(user);

			return Response.ok().build();

		} catch (Exception e) {

			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	/*
	 * Método para listar todas pessoas cadastradas no banco de dados
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@ApiOperation(value="Lista todos users",
	notes="Lista todos users")
	@ApiResponses(value= {@ApiResponse(code=200, message="Usuários listados com sucesso"),
			@ApiResponse(code=400, message="Requisição Inválida!")})
	public Response getUsers() {
		List<UserXML> users = new ArrayList<UserXML>();

		List<User> usersEntityList = repository.listAll();

		for (User entity : usersEntityList) {
			users.add(new UserXML(entity.getId(), entity.getName(), entity.getEmail(), entity.isStatus()));
		}

		if(users!=null) {
			return Response.ok(users).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	/*
	 * Esse método busca um usuário cadastrado no banco de dados pelo seu ID
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUser/{id}")
	@ApiOperation(value="Seleciona um único usuário pelo seu ID",
	notes="Seleciona um único usuário pelo seu ID")
	public UserXML getUser(@ApiParam(value = "Armazena o ID do usuário", required = true) @PathParam("id") Long id) {
		User userEntity = repository.getUser(id);

		if (userEntity != null)
			return new UserXML(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.isStatus());
		return null;
	}

	/*
	 * Excluindo um usuário baseado em seu id
	 */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/delete/{id}")
	@ApiOperation(value="Remove um usuário",
	notes="Remove um usuário",
	response=String.class)
	public String delete(@ApiParam(value = "Armazena o ID do usuário", required = true) @PathParam("id") Long id) {
		try {
			repository.delete(id);

			return "Registro excluido com sucesso!!!";
		} catch (Exception e) {
			return "Erro ao excluir o registro! " + e.getMessage();
		}
	}

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response getUser() { try {
	 * UserDAO dao = new UserDAO(); List<User> users = dao.getUsers(); JSONArray
	 * userJsonArray = usersToJsonArray(users); return
	 * Response.ok(userJsonArray).build(); } catch (Exception e) { return
	 * buildInternalErrorResponse(e); } }
	 * 
	 * 
	 * @POST
	 * 
	 * @Path("/create")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response addUser(User user)
	 * { user.setName(user.getName()); user.setEmail(user.getEmail());
	 * user.setStatus(true);
	 * 
	 * UserDAO dao = new UserDAO(); dao.addUser(user);
	 * 
	 * return Response.ok().build(); }
	 * 
	 * @DELETE
	 * 
	 * @Path("/delete/{id}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response
	 * deleteUser(@PathParam("id") Long id) { UserDAO dao = new UserDAO(); int
	 * count = dao.deleteUser(id); if (count == 0) { return
	 * Response.status(Response.Status.BAD_REQUEST).build(); } else return
	 * Response.ok().build(); }
	 */
}
