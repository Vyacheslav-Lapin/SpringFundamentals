package lab.dao;

import lab.model.Country;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public interface CountryDao extends InitializingBean {
    List<Country> getCountryList();

    List<Country> getCountryListStartWith(String name);

    void updateCountryName(String codeName, String newCountryName);

    void loadCountries();

    Country getCountryByCodeName(String codeName);

    Country getCountryByName(String name)
            throws CountryNotFoundException;
}
