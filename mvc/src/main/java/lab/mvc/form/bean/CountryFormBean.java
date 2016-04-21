package lab.mvc.form.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CountryFormBean {

    @NotNull(message="{NotNull.countryFormBean.name}")
    @Size(min = 2, max = 255)
    private String name;

    @NotNull (message="{NotNull.countryFormBean.code}")
    @Size(min = 2, max = 20)
    private String code;
}
