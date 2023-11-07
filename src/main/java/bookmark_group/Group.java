package bookmark_group;

import lombok.Data;

@Data
public class Group {
	private int id;
	private String name;
	private int orderRow;
	private String registerDate;
	private String editDate;
}
