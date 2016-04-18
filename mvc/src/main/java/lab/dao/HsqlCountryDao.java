package lab.dao;

import lab.model.Country;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class HsqlCountryDao extends JdbcDaoSupport implements CountryDao {

    private static Log log = LogFactory.getLog(HsqlCountryDao.class);


    @Override
    public List<Country> getCountryList() {
        return null;
    }

    @Override
    public List<Country> getCountryListStartWith(String name) {
        return null;
    }

    @Override
    public void updateCountryName(String codeName, String newCountryName) {

    }

    @Override
    public void loadCountries() {

    }

    @Override
    public Country getCountryByCodeName(String codeName) {
        return null;
    }

    @Override
    public Country getCountryByName(String name) throws CountryNotFoundException {
        return null;
    }
}
