package fr.nocturlab.utils;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingUtil {
	@Autowired
	private ObjectMapper mapper;

	public <T extends Object> T create(Class<T> c, Map<String, Object> data) {
		// Conversion de la map en object
		return mapper.convertValue(data, c);
	}
	
	public <T extends Object> T patch(T o, Map<String, Object> data) {
		if (o==null || data==null)
			throw new NullPointerException();
		
		// Conversion de l'objet en Map
		Map<String, Object> modelAsMap = convertToMap(o); 

		// Merge des données
		modelAsMap.putAll(data);

		// Récupération de la classe de l'objet
		@SuppressWarnings("unchecked")
		Class<T> c = (Class<T>) o.getClass();

		// Conversion de la map en object
		return mapper.convertValue(modelAsMap, c);
	}

	public <T extends Object> Map<String, Object> convertToMap(T o) {
		return mapper.convertValue(o, new TypeReference<Map<String, Object>>() {}); 
	}
}