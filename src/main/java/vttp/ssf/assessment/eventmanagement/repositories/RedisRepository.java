package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired @Qualifier("redisTemplate")
	private RedisTemplate<String, Object> template;
	
	//rpush eventList event
	public void saveRecord(Event event) {
		
		ListOperations<String, Object> listOps = template.opsForList();

        listOps.rightPush("eventList", event);
	}

	// llen eventList
	public Long getNumberOfEvents() {

		ListOperations<String, Object> listOps = template.opsForList();

		Long numberOfEvents = listOps.size("eventList");

		return numberOfEvents;
	}

	// lindex eventList index
	public Event getEvent(Integer index) {

		ListOperations<String, Object> listOps = template.opsForList();

		Event event = (Event) listOps.index("eventList", index);

		return event;
	}
}
