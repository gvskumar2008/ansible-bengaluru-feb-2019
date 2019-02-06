package org.tektutor;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("training")
public class TrainingController {
	private TrainingService trainingService = new TrainingService();

	@GET
	@Produces ( MediaType.APPLICATION_JSON )
	public ArrayList<Training> getAllTrainings() {
		return trainingService.getAllTrainings();
	}

	@GET
	@Path("{id}")
	@Produces ( MediaType.APPLICATION_JSON )
	public Training getTraining( @PathParam("id") int id ) {
		return trainingService.getTraining(id);
	}

	@PUT
	@Produces ( MediaType.APPLICATION_JSON )
	public int updateTraining( Training training ) {
		return trainingService.updateTraining(training);
	}

	@POST
	@Produces ( MediaType.APPLICATION_JSON )
	public int addTraining( Training training ) {
		return trainingService.addTraining(training);
	}

	@DELETE
	@Path("{id}")
	@Produces ( MediaType.APPLICATION_JSON )
	public int deleteTraining( @PathParam("id") int id ) {
		return trainingService.deleteTraining(id);
	}
}
