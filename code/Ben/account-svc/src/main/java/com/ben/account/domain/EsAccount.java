package com.ben.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lomofu
 * @date 2020/3/12 20:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
@Document(indexName = "account_index", type = "account")
public class EsAccount implements Serializable {

  private static final long serialVersionUID = -7302875683056726862L;

  @Id
  @Field(type = FieldType.Keyword, store = true)
  private String id;

  @Field(
      type = FieldType.Keyword,
      store = true,
      analyzer = "ik_max_word",
      searchAnalyzer = "ik_max_word")
  private String name;

  @Field(type = FieldType.Keyword, store = true)
  private String email;

  @JsonProperty("phone_number")
  @Field(name = "phone_number", type = FieldType.Keyword, store = true)
  private String phoneNumber;

  @JsonProperty("gender")
  @Field(type = FieldType.Boolean, store = true)
  private boolean gender;

  @JsonProperty("avatar_url")
  @Field(name = "avatar_url", type = FieldType.Keyword, store = true)
  private String avatarUrl;

  @JsonProperty("create_time")
  @Field(name = "create_time", type = FieldType.Date, store = true)
  private Date createTime;

  @JsonProperty("is_active")
  @Field(name = "is_active", type = FieldType.Boolean, store = true)
  private boolean active;

  @JsonProperty("is_admin")
  @Field(name = "is_admin", type = FieldType.Boolean, store = true)
  private boolean admin;

  @JsonProperty("company_id")
  @Field(name = "company_id", type = FieldType.Keyword, store = true)
  private String companyId;

  @JsonProperty("company_name")
  @Field(name = "company_name", type = FieldType.Keyword, store = true)
  private String companyName;
}
