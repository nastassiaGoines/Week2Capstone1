import java.time.LocalDate;

public class Task {

	private String name;
	private String description;
	private LocalDate date;
	private boolean complete;
	
	
	public Task(String name, String description, LocalDate date) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.complete = false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String isComplete() {
		if (complete)
		return "Complete";
		else
		return "Incomplete";
	}


	public void setComplete() {
		this.complete = true;
	}

	public void setIncomplete() {
		this.complete = false;
	}

	@Override
	public String toString() {
		return name + description + date.format(TaskValidation.f).toString() + complete;
	}


}
