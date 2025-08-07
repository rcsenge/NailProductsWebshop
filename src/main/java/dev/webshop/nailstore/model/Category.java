package dev.webshop.nailstore.model;

import dev.webshop.nailstore.util.SlugUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String slug;

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
		this.slug = SlugUtil.slugify(name);
	}

	public void setName(String name) {
		this.name = name;
		this.slug = SlugUtil.slugify(name);
	}
}
