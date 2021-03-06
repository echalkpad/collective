package com.collective.permanentsearch.persistence.dao;

import com.collective.permanentsearch.persistence.configuration.ConfigurationManager;
import com.collective.permanentsearch.persistence.configuration.PermanentSearchPersistenceConfiguration;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author Matteo Moci ( matteo.moci (at) gmail.com )
 */
public abstract class AbstractDaoTestCase {

    private final static Logger logger = Logger.getLogger(AbstractDaoTestCase.class);

    //a shared configuration to be accessed from all extending classes
    protected PermanentSearchPersistenceConfiguration messagesPersistenceConfiguration;

    private ConfigurationManager configurationManager;
    private static final String CONFIG_FILE = "permanent-search-persistence-configuration-tests.xml";

    protected AbstractDaoTestCase() {
        String curDir = System.getProperty("user.dir");
        logger.debug(curDir);
        configurationManager = ConfigurationManager.getInstance(CONFIG_FILE);
        messagesPersistenceConfiguration = configurationManager.getPermanentSearchPersistenceConfiguration();
    }

    @BeforeClass
    public void setUp(){
        logger.debug("starting messages daos tests");
    }

    @AfterClass
    public void tearDown() {
        logger.debug("ended messages daos tests");
    }
}
