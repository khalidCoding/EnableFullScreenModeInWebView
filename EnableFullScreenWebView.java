private class ChromeClient extends WebChromeClient {
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    protected FrameLayout mFullscreenContainer;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;

    ChromeClient() {}

    public Bitmap getDefaultVideoPoster()
    {
        if (mCustomView == null) {
            return null;
        }
        return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
    }

    public void onHideCustomView()
    {
        ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
        this.mCustomView = null;
        getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        setRequestedOrientation(this.mOriginalOrientation);
        this.mCustomViewCallback.onCustomViewHidden();
        this.mCustomViewCallback = null;
    }

    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
        if (this.mCustomView != null)
        {
            onHideCustomView();
            return;
        }
        this.mCustomView = paramView;
        this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        this.mOriginalOrientation = getRequestedOrientation();
        this.mCustomViewCallback = paramCustomViewCallback;
        ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
        getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}