package de.hybris.platform.addons.randomimageaddon.component.renderer;

import de.hybris.platform.addons.randomimageaddon.model.RandomImageParagraphComponentModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;


public class RandomImageParagraphComponentRenderer<C extends RandomImageParagraphComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{

	CMSComponentService cmsComponentService;
	ModelService modelService;

	@Override
	@Required
	public void setCmsComponentService(final CMSComponentService cmsComponentService)
	{
		this.cmsComponentService = cmsComponentService;
	}

	@Override
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	//
	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		for (final String property : cmsComponentService.getEditorProperties(component))
		{
			try
			{
				final Object value = modelService.getAttributeValue(component, property);
				variables.put(property, value);

			}
			catch (final AttributeNotSupportedException ignore)
			{
				// ignore
			}
		}
		return variables;
	}
}