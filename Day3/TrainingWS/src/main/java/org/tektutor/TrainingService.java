package org.tektutor;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingService {

	private Connection connect= new DatabaseConnectionManager().getConnection();

	public ArrayList<Training> getAllTrainings() {

		ArrayList<Training> listOfTrainings = new ArrayList<Training>();

		try {
			PreparedStatement ps 
				= connect.prepareStatement ( "select * from training" );

			ResultSet rs = ps.executeQuery();

			while ( rs.next() ) {
				Training training = new Training();

				training.setId ( rs.getInt(1) );
				training.setName ( rs.getString(2) );
				training.setDuration ( rs.getString(3) );

				listOfTrainings.add ( training );
			}

			connect.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return listOfTrainings;

	}

	public Training getTraining(int id) {

		Training training = null;

		try {
			PreparedStatement ps 
				= connect.prepareStatement ( "select * from training where id=?" );

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if ( rs.next() ) {
				training = new Training();

				training.setId ( rs.getInt(1) );
				training.setName ( rs.getString(2) );
				training.setDuration ( rs.getString(3) );
			}

			connect.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return training;

	}


	public int updateTraining(Training training) {
		int status = 0;

		try {
			PreparedStatement ps 
				= connect.prepareStatement ( "update training set name=?, duration=? where id=?" );

			ps.setString( 1, training.getName() );
		        ps.setString( 2, training.getDuration() );
			ps.setInt   ( 3, training.getId() );	

			status = ps.executeUpdate();

			connect.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return status;

	}

	public int addTraining(Training training) {
		int status = 0;

		try {
			PreparedStatement ps 
				= connect.prepareStatement ( "insert into training(id,name,duration) values(?, ?, ?)" );

			ps.setInt    ( 1, training.getId() );
			ps.setString ( 2, training.getName() );	
		        ps.setString ( 3, training.getDuration() );

			status = ps.executeUpdate();

			connect.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return status;
	}

	public int deleteTraining(int id) {
		int status = 0;

		try {
			PreparedStatement ps 
				= connect.prepareStatement ( "delete from training where id=?" );

			ps.setInt( 1, id );	

			status = ps.executeUpdate();

			connect.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return status;

	}
}
