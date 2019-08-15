
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Conference;

@Service
@Transactional
public class ActivityService {

	@Autowired
	private ActivityRepository	activityRepository;
	@Autowired
	private Validator			validator;


	public Activity create() {
		final Activity activity = new Activity();
		activity.setTitle("");
		activity.setSpeaker("");
		activity.setDuration(0);
		activity.setSchedule(new Date());
		activity.setRoom("");
		activity.setSummary("");
		activity.setAttachments(new HashSet<String>());
		activity.setConference(new Conference());
		return activity;
	}

	public Collection<Activity> findAll() {
		return this.activityRepository.findAll();
	}

	public Activity findOne(final Integer id) {
		return this.activityRepository.findOne(id);
	}

	public Activity save(final Activity activity) {
		final Activity saved = this.activityRepository.save(activity);
		return saved;
	}

	public Activity reconstruct(final Activity activity, final BindingResult binding) {
		Activity res;

		if (activity.getId() == 0) {
			res = activity;
			this.validator.validate(res, binding);

		} else {
			res = this.activityRepository.findOne(activity.getId());
			final Activity copy = new Activity();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setTitle(activity.getTitle());
			copy.setSpeaker(activity.getSpeaker());
			copy.setDuration(activity.getDuration());
			copy.setSchedule(activity.getSchedule());
			copy.setRoom(activity.getRoom());
			copy.setSummary(activity.getSummary());
			copy.setAttachments(activity.getAttachments());
			copy.setConference(activity.getConference());
			this.validator.validate(copy, binding);

			res = copy;
		}
		return res;

	}
}
