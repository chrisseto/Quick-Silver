package com.chrisseto.tilttolive.util;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.SingleValueSpanEntityModifier;
import org.andengine.util.modifier.IModifier;

public class HeightModifier extends SingleValueSpanEntityModifier{
	float x,y;
	
	public HeightModifier(float pDuration, float pFromValue, float pToValue) {
		super(pDuration, pFromValue, pToValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSetInitialValue(IEntity pItem, float pValue) {
		x = pItem.getX();
		y = pItem.getY()+pItem.getHeight();
		pItem.setHeight(pValue);

	}

	@Override
	protected void onSetValue(IEntity pItem, float pPercentageDone, float pValue) {
		pItem.setX(x);
		pItem.setY(y);
		pItem.setHeight(pValue);
		
	}

	@Override
	public HeightModifier deepCopy()
			throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
