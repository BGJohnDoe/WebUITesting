package rest;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookModel {
	String isbn;
	String title;
	String subTitle;
	String author;
	Date publish_date; //($date-time)
	String publisher;
	int pages;
	String description;
	String website;
}
