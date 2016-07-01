package com.example.d_changealphabyscroll;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.d_changealphabyscroll.widget.ObservableScrollView;
import com.example.d_changealphabyscroll.widget.ObservableScrollView.ScrollViewListener;

/**
 * android 透明Title栏实现效果展示
 */
public class MainActivity extends Activity implements ScrollViewListener{
	
	private View layoutHead;
	private ObservableScrollView scrollView;
	private ImageView imageView;
	private WebView webView;

	private int height ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	
	private void initView() {
		webView = (WebView) findViewById(R.id.webview1);
		scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
		layoutHead = findViewById(R.id.view_head);
		imageView = (ImageView) findViewById(R.id.imageView1);
		layoutHead.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
		
		//��ʼ��webview
		//����֧��javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.topit.me/");
        //����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
        webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
               //����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
             view.loadUrl(url);
            return true;
        }
       });

		
		//��ȡ����ͼƬ�߶Ⱥ����ù�������
		ViewTreeObserver vto = imageView.getViewTreeObserver();  
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override  
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height =   imageView.getHeight();
                imageView.getWidth();
                
                scrollView.setScrollViewListener(MainActivity.this);
            }  
        });
        
        
       
	}


	@Override
	public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
			int oldx, int oldy) {
		
//		Log.i("TAG","y--->"+y+"    height-->"+height);
		if(y<=height){
			float scale =(float) y /height;
			float alpha =  (255 * scale);
//			Log.i("TAG","alpha--->"+alpha);
			
			//layoutȫ��͸��
//			layoutHead.setAlpha(scale);
			
			//ֻ��layout����͸��(��֪������Ч��)
			layoutHead.setBackgroundColor(Color.argb((int) alpha, 0xfd, 0x91, 0x5b));
		}
		
	}
}
