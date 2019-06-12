package com.silence;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by silence on 2018/3/27.
 */
public class FChromeDriver extends ChromeDriver {


    public FChromeDriver() {
        super();
    }

    public FChromeDriver(ChromeDriverService service) {
        super(service);
    }

    public FChromeDriver(Capabilities capabilities) {
        super(capabilities);
    }

    public FChromeDriver(ChromeOptions options) {
        super(options);
    }

    public FChromeDriver(ChromeDriverService service, ChromeOptions options) {
        super(service,options);
    }

    public FChromeDriver(ChromeDriverService service, Capabilities capabilities) {
       super(service,capabilities);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        try {
            this.close();
        } catch (Exception e) {
        }

    }
}
