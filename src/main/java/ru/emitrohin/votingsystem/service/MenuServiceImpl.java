package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.interfaces.MenuRepository;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository repository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository) {
        this.repository = repository;
    }

    @Override
    public Menu save(Menu menu, int id) {
        Assert.notNull(menu, "user must not be null");
        return repository.save(menu, id);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Menu get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

}
