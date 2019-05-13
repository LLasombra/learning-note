package basical;

public interface Service {
	void addNew(Person person);

	void delete(Integer id);

	void update(Person person);
	
	void printPersons();
}
