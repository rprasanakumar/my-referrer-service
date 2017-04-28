package org.service.referrer.service;

import java.net.URISyntaxException;
import java.util.List;

import org.service.referrer.model.ReferrerURL;

public interface IReferrerService {
	
	public ReferrerURL putDataURL() throws URISyntaxException;

	public List<ReferrerURL> getTopURL();
}
