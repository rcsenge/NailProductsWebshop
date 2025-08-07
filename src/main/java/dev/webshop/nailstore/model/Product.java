package dev.webshop.nailstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String slug;

	@Column(name = "image_file")
	private String imageFile;

	private int price;

	@Lob
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
