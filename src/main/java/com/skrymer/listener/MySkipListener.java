package com.skrymer.listener;

import com.skrymer.model.Owner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.SkipListener;

/**
 * Created by nielses on 12/07/16.
 */
public class MySkipListener implements SkipListener<Owner, Owner> {
  private static final Log LOG = LogFactory.getLog(MySkipListener.class);

  public void onSkipInRead(Throwable throwable) {
    LOG.warn("Read was skipped");
  }

  public void onSkipInWrite(Owner owner, Throwable throwable) {
    LOG.warn("Write was skipped for owner: " + owner);
  }

  public void onSkipInProcess(Owner owner, Throwable throwable) {
    LOG.warn("Process was skipped for owner: " + owner);
  }
}
