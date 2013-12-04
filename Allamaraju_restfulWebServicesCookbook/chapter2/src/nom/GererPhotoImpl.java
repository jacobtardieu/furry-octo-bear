package nom;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class GererPhotoImpl implements GererPhoto{
	private static List<Photo> photos = null;
	
	public GererPhotoImpl(){
		if (null == photos){
			photos = new ArrayList<Photo>();
			Photo p = new Photo();
			p.setId(0);
			p.setName("dada");
			photos.add(p);
		}
	}
	
	@Override
	public Photo getPhoto(long id) {
		for (Photo p: photos){
			if (p.getId() == (int)id){
				return p;
			}
		}
		return null;
	}

	@Override
	public Response updatePhoto(long id, Photo photo) {
		for (Photo p: photos){
			if (p.getId() == (int)id){
				p.setName(photo.getName());
				return Response.ok().build();
			}
		}
		return Response.notModified().build();
	}

	@Override
	public Response addPhoto(Photo photo) {
		photos.add(photo);
		return Response.ok().build();
	}

	@Override
	public Response deletePhoto(long id) {
		for (Photo p: photos){
			if (p.getId() == (int)id){
				photos.remove(p);
				return Response.ok().build();
			}
		}
		return Response.notModified().build();
	}

}
