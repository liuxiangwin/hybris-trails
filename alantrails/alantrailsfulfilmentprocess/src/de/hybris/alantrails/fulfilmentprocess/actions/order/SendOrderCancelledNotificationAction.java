/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package de.hybris.alantrails.fulfilmentprocess.actions.order;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.alantrails.fulfilmentprocess.events.OrderCancelledEvent;

public class SendOrderCancelledNotificationAction extends AbstractProceduralAction<OrderProcessModel>{
	private static final Logger LOG = Logger.getLogger(SendOrderCancelledNotificationAction.class);

	private EventService eventService;

	@Override
	public void executeAction(final OrderProcessModel process)
	{
		getEventService().publishEvent(new OrderCancelledEvent(process));
		if (LOG.isInfoEnabled())
		{
			LOG.info("Process: " + process.getCode() + " in step " + getClass());
		}
	}

	protected EventService getEventService()
	{
		return eventService;
	}

	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}
}
