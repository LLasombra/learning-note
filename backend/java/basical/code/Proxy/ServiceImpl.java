package basical;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServiceImpl implements Service {

	Map<Integer, Person>persons=new HashMap<Integer, Person>();
	
	
	public ServiceImpl() {
		persons.put(1001, new Person("张壮壮",1001));
		persons.put(1002, new Person("张壮壮",1002));
	}

	@Override
	public void addNew(Person person) {
		persons.put(person.getAge(), person);
	}

	@Override
	public void delete(Integer id) {
		if(persons.keySet().contains(id)){
			persons.remove(id);
		}else
			throw new RuntimeException("要删除的人员不存在！");
		
		
	}

	@Override
	public void update(Person person) {
		persons.put(person.getAge(), person);
		
	}
	
	public void printPersons(){
		Iterator<Map.Entry<Integer, Person>>enties=persons.entrySet().iterator();
		while(enties!=null){
			Map.Entry<Integer, Person>entry=enties.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Service service=new ServiceImpl();
		service.delete(1005);
		service.printPersons();
	}
}
