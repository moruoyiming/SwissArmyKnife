package com.wanjian.sak.system.rendernode;

import android.graphics.Canvas;
import android.view.DisplayListCanvas;
import android.view.HardwareCanvas;
import android.view.RenderNode;

import java.lang.reflect.Method;

public class RenderNodeV21Impl extends RenderNodeCompact {

  private RenderNode renderNode;

  public RenderNodeV21Impl(String name) {
    renderNode = RenderNode.create(name, null);
  }

  @Override
  public void drawRenderNode(Canvas canvas) {
    ((HardwareCanvas) canvas).drawRenderNode(renderNode);
  }

  @Override
  public HardwareCanvas beginRecording(int width, int height) {
    renderNode.setLeftTopRightBottom(0, 0, width, height);
    try {
      Method method = RenderNode.class.getDeclaredMethod("start", int.class, int.class);
      method.setAccessible(true);
      return (HardwareCanvas) method.invoke(renderNode, width, height);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void endRecording(Canvas canvas) {
    renderNode.end((HardwareCanvas) canvas);
  }

  @Override
  public boolean isValid() {
    return renderNode.isValid();
  }
}
