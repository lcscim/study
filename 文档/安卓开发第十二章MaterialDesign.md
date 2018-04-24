#Material Design实战
###1.Toolbar

	Theme.AppCompat.Light.NoActionBar		//将背景颜色设置成为浅色，并且无actionbar
	Theme.AppCompat.NoActionBar				//将背景颜色设置成为深色，并且无actionbar
colorPrimary（actionbar背景颜色），colorPrimaryDark（状态栏颜色），colorAccent（浮动在界面上的控件颜色，即悬浮按钮颜色），textcolorPrimary（actionbar上文字颜色），windowBackground（主界面背景颜色），navigationBarColor（虚拟按键背景颜色）

	<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"		//由于存在该命名空间，所有有Android：id等写法
	    xmlns:app="http://schemas.android.com/apk/res-auto"		//此条同上可以使用app:popupTheme
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <android.support.v7.widget.Toolbar	
	        android:id="@+id/toolbar"
	        android:layout_width="match_parent"
	        android:layout_height="?attr/actionBarSize"		//高度设置和actionbar高度相同
	        android:background="?attr/colorPrimary"		
	        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"	//由于在Androidmanifest指定背景为浅色，所以此时字体为深色，如果需要设置为浅色，则需要指定该属性
	        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>	//将单独弹出的菜单栏设置为浅色背景

	</FrameLayout>
然后在主程序代码上添加：
	
	Toolbar toolbar = findViewById(R.id.toolbar);		
    setSupportActionBar(toolbar);		//将toolbar实例传入
在Androidmanifest中的activity标签下添加label属性，用于指定toolbar中文字内容，没有指定会默认使用application中指定的label内容<br/>
在res目录下新建menu文件夹，在里面新建toolbar.xml文件如下

	<menu xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto">
	
	    <item android:id="@+id/backup"
	        android:icon="@drawable/backup"
	        android:title="Backup"
	        app:showAsAction="always"/>		//指定显示位置，always表示永远显示，ifroom表示屏幕空间够就显示在屏幕中，不够在菜单当中，never表示永远显示在菜单中
	    <item android:id="@+id/delete"
	        android:icon="@drawable/delete"
	        android:title="Delete"
	        app:showAsAction="ifRoom"/>
	    <item android:id="@+id/settings"
	        android:icon="@drawable/settings"
	        android:title="Settings"
	        app:showAsAction="never"/>

	</menu>
然后在主程序代码上添加：

	public boolean onCreateOptionsMenu(Menu menu) {			//加载toolbar菜单文件
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {		//设置各个按钮点击事件
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
###2.滑动菜单(侧滑菜单)
####2.1.DrawerLayout
在之前的布局文件添加

	<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_height="match_parent"
	    android:layout_width="match_parent"
	    android:id="@+id/drawer_layout">
	    
	    <FrameLayout
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	
	        <android.support.v7.widget.Toolbar
	            ...
	            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>

    	</FrameLayout>
	    
	    <TextView
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:text="This is menu"
	        android:textSize="30sp"
	        android:background="#FFF" 
	        android:layout_gravity="start"/>		//该属性必须指定，left表示从左往右滑动，right从右往左，start指定根据系统语言判断，语言从左往右，DrawerLayout也从左往右
	</android.support.v4.widget.DrawerLayout>
在toolbar左侧添加菜单图标用以提醒有侧滑菜单

	public class MainActivity extends AppCompatActivity {
	    private DrawerLayout mDrawerLayout;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Toolbar toolbar = findViewById(R.id.toolbar);
	        setSupportActionBar(toolbar);
	        mDrawerLayout = findViewById(R.id.drawer_layout);	//获取drawerlayout实例
	        ActionBar actionBar = getSupportActionBar();		//获取actionbar实例
	        if (actionBar!=null){
	            actionBar.setDisplayHomeAsUpEnabled(true);		//将导航按钮显示出来
	            actionBar.setHomeAsUpIndicator(R.drawable.menu);	//设置导航按钮图标
	        }
	    }
	
	    ...
	
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()){
	            ...
	            case android.R.id.home:
	                mDrawerLayout.openDrawer(GravityCompat.START);		//将其展现出来
	                break;
	            default:
	                break;
	        }
	        return true;
	    }
	}
####2.2NavigationView
在app/build.gradle文件中添加

	implementation 'com.android.support:design:26.1.0'			//design support库包含NavigationView
    implementation 'de.hdodenhof:circleimageview:2.1.0'			//生成圆形图片需要导入的包
在menu文件夹下新建nav_menu.xml文件

	<menu xmlns:android="http://schemas.android.com/apk/res/android">
	    <group android:checkableBehavior="single">		//group表示一个组，checkableBehavior="single"表示单选
	    <item android:id="@+id/nav_call"
	        android:icon="@drawable/call"
	        android:title="Call"/>
	    <item android:id="@+id/nav_friends"
	        android:icon="@drawable/friends"
	        android:title="Friends"/>
	    <item android:id="@+id/nav_location"
	        android:icon="@drawable/location"
	        android:title="Location"/>
	    <item android:id="@+id/nav_mail"
	        android:icon="@drawable/mail"
	        android:title="Mail"/>
	    <item android:id="@+id/nav_task"
	        android:icon="@drawable/task"
	        android:title="Task"/>
	    </group>
	</menu>
在layout文件下新建nav_header.xml文件

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:padding="100dp"
	    android:background="?attr/colorPrimary">
	    <de.hdodenhof.circleimageview.CircleImageView
	        android:id="@+id/icon_image"
	        android:layout_width="100dp"
	        android:layout_height="100dp"
	        android:src="@drawable/nav_icon"
	        android:layout_centerInParent="true"/>
	    <TextView
	        android:id="@+id/mail"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_alignParentBottom="true"
	        android:text="lcscim@gmail.com"
	        android:textColor="#fff"
	        android:textSize="14sp"/>
	    <TextView
	        android:id="@+id/usrname"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_above="@+id/mail"
	        android:text="lcscim"
	        android:textColor="#fff"
	        android:textSize="14sp"/>

	</RelativeLayout>
在主活动页面添加NavigationView
	
	<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_height="match_parent"
	    android:layout_width="match_parent"
	    android:id="@+id/drawer_layout">
	
	    <FrameLayout
	        ....
	    </FrameLayout>
	
	    <android.support.design.widget.NavigationView
	        android:id="@+id/nav_view"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:layout_gravity="start"
	        app:menu="@menu/nav_menu"		//设置menu
	        app:headerLayout="@layout/nav_header"/>		//设置header
	</android.support.v4.widget.DrawerLayout>
最后修改主程序

	public class MainActivity extends AppCompatActivity {
	    private DrawerLayout mDrawerLayout;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Toolbar toolbar = findViewById(R.id.toolbar);
	        setSupportActionBar(toolbar);
	        mDrawerLayout = findViewById(R.id.drawer_layout);
	        NavigationView navigationView = findViewById(R.id.nav_view);
	        ActionBar actionBar = getSupportActionBar();
	        if (actionBar!=null){
	            actionBar.setDisplayHomeAsUpEnabled(true);
	            actionBar.setHomeAsUpIndicator(R.drawable.menu);
	        }
	        navigationView.setCheckedItem(R.id.nav_call);   //将call菜单设置为默认选择中
	        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
	            @Override
	            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
	                mDrawerLayout.closeDrawers();       //关闭滑动菜单
	                return true;
	            }
	        });
	    }
	...
	}
###3.悬浮按钮和可交互提示
####3.1FloatingActionButton
一般是在界面右下角浮在界面上。

	<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_height="match_parent"
	    android:layout_width="match_parent"
	    android:id="@+id/drawer_layout">
	
	    <FrameLayout
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	
	        ...
	        <android.support.design.widget.FloatingActionButton
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:id="@+id/fab"
	            android:layout_gravity="bottom|end"		//end的作用和right一样
	            android:layout_margin="16dp"
	            android:src="@drawable/done"	
	            app:elevation="8dp"/>		//设置floatingactionbutton悬浮在界面上的高度
	
	    </FrameLayout>
	
	    ...
	</android.support.v4.widget.DrawerLayout>
设置点击事件

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ...
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked fab",Toast.LENGTH_SHORT).show();
            }
        });
        ...
        });
    }
####3.2snackbar
和toast类似，只不过可以提供一个额外的按钮的点击事件
	
	FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Date deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {		//make方法第一个参数是view，当前界面任意一个view都可以，第二个参数第三个参数都和toast类似。调用setaction设置一个动作，第一个参数是可以点击的文本，即弹出undo的按钮
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Date deleted",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
####3.3CoordinatorLayout
可以说是加强版的FrameLayout，会监控其所有子控件的各种点击事件，然后为我们做出最合理的相应，比如上一小节snackbar把FloatingActionButton遮盖住了，使用CoordinatorLayout可解决，修改布局如下

    <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>
        <android.support.design.widget.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/fab"
            android:layout_gravity="bottom|end"
            android:layout_margin="16dp"
            android:src="@drawable/done"
            app:elevation="8dp"/>

    </android.support.design.widget.CoordinatorLayout>
###4.卡片式布局
####4.1CardView
实际上Cardview也是一个frameLayout,只是额外提供了圆角和阴影等效果。使用cardview需要在build.gradle文件中声明对这些库的依赖，

    implementation 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:cardview-v7:26.1.0'
	implementation 'com.github.bumptech.glide:glide:3.7.0'		//图片加载库，用法简单，功能强大，可加载本地图片，视频，网络图片，
在主布局插入recyclerview

	<android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v7.widget.Toolbar
            ...
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
        <android.support.design.widget.FloatingActionButton
            ...
            app:elevation="8dp"/>

    </android.support.design.widget.CoordinatorLayout>
定义一个实体类

	public class Fruit {
	    private String name;
	    private int imageId;
	
	    public Fruit(String name,int imageId){
	        this.name = name;
	        this.imageId = imageId;
	    }
	    public String getName() {
	        return name;
	    }
	
	    public int getImageId() {
	        return imageId;
	    }
	}
为recyclerview的子项制定一个自定义布局

	<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content"
	    android:layout_margin="5dp"
	    app:cardCornerRadius="4dp">
	
	    <LinearLayout
	        android:orientation="vertical"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content">
	        <ImageView
	            android:id="@+id/fruit_image"
	            android:layout_width="match_parent"
	            android:layout_height="100dp"
	            android:scaleType="centerCrop"/>	//指定图片保持原有比例充满imageview
	        <TextView
	            android:id="@+id/fruit_name"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:layout_gravity="center_horizontal"
	            android:layout_margin="5dp"
	            android:textSize="16sp"/>
	    </LinearLayout>
	</android.support.v7.widget.CardView>
为recyclerview新建适配器

	public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
	    private Context mContext;
	    private List<Fruit> mFruitList;
	    static class ViewHolder extends RecyclerView.ViewHolder{
	        CardView cardView;
	        ImageView fruitImage;
	        TextView fruitName;
	        public ViewHolder(View itemView) {
	            super(itemView);
	            cardView = (CardView) itemView;
	            fruitImage = itemView.findViewById(R.id.fruit_image);
	            fruitName = itemView.findViewById(R.id.fruit_name);
	        }
	    }
	    public FruitAdapter(List<Fruit> fruitList){
	        mFruitList = fruitList;
	    }
	    @Override
	    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	        if (mContext == null){
	            mContext = parent.getContext();
	        }
	        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
	        return new ViewHolder(view);
	    }
	
	    @Override
	    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
	        Fruit fruit = mFruitList.get(position);
	        holder.fruitName.setText(fruit.getName());
	        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);	//使用glide加载图片
	    }
	
	    @Override
	    public int getItemCount() {
	        return mFruitList.size();
	    }
	}
最后修改主程序代码
	
	public class MainActivity extends AppCompatActivity {
	    private DrawerLayout mDrawerLayout;
	    private Fruit[] fruits = {new Fruit("Apple",R.drawable.apple),new Fruit("Banana",R.drawable.banana),
	            new Fruit("Orange",R.drawable.orange),new Fruit("Watermelon",R.drawable.watermelon),
	            new Fruit("Pear",R.drawable.pear),new Fruit("Grape",R.drawable.grape),
	            new Fruit("Pineapple",R.drawable.pineapple),new Fruit("Strawberry",R.drawable.strawberry),
	            new Fruit("Cherry",R.drawable.cherry),new Fruit("Mango",R.drawable.mango)};
	    private List<Fruit> fruitList = new ArrayList<>();
	    private FruitAdapter adapter;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        ...
	        initFruits();
	        RecyclerView recyclerView = findViewById(R.id.recycler_view);
	        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
	        recyclerView.setLayoutManager(layoutManager);
	        adapter = new FruitAdapter(fruitList);
	        recyclerView.setAdapter(adapter);
	    }
	
	    private void initFruits() {
	        fruitList.clear();
	        for (int i = 0;i<50;i++){
	            Random random =new Random();
	            int index = random.nextInt(fruits.length);
	            fruitList.add(fruits[index]);
	        }
	    }
		...
	}
####4.2.AppBarLayout
取消遮挡toolbar，需要appbarlayout,相当于垂直方向上的LinearLayout，做了更好的封装，取消遮盖第一步将toolbar嵌套到appbarlayout
第二步，指定recyclerview的布局行为

	<android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

        <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
				app:layout_scrollFlags="scroll|enterAlways|snap"/>	//表示是否自动隐藏toolbar，值为scroll时，toolbar会跟着一齐向上滚并实现隐藏 ，enteralways表示向下滚动时会跟着一块向下滚动并成功显示，snap表示会根据当前滚动的距离判断是否隐藏还是显示
    </android.support.design.widget.AppBarLayout>
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>	//指定布局行为
###5.下拉刷新
下拉刷新需要依靠SwipeRefreshLayout，把想要实现下拉刷新的控件放到SwipeRefreshLayout中

	<android.support.v4.widget.SwipeRefreshLayout
        android:id="@+id/swipe_refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">	//由于recyclerview是swiperefreshlayout的子控件，所以把behavior属性移动到swiperefreshlayout上

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    </android.support.v4.widget.SwipeRefreshLayout>
在代码块中处理刷新逻辑

	public class MainActivity extends AppCompatActivity {
	    ...
	    private SwipeRefreshLayout swipeRefresh;
	    @SuppressLint("ResourceAsColor")
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        ...
	        swipeRefresh = findViewById(R.id.swipe_refresh);        //找到swiperefreshlayout的实例
	        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);    //下拉刷新进度条颜色
	        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
	            @Override           
	            public void onRefresh() {       //下拉刷新监听器，监听动作
	                refreshFruits();    //调用具体方法
	            }
	        });
	    }
	
	    private void refreshFruits() {
	        new Thread(new Runnable() {     //刷新本地数据，
	            @Override
	            public void run() {
	                try {
	                    Thread.sleep(2000);     //由于特别快，所以使用sleep方法沉睡线程2秒钟
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                runOnUiThread(new Runnable() {      //将线程切换到主线程
	                    @Override
	                    public void run() {
	                        initFruits();       //重新生成数据
	                        adapter.notifyDataSetChanged();     //通知数据发生了变化
	                        swipeRefresh.setRefreshing(false);      //表示刷新时间结束，隐藏进度条
	                    }
	                });
	            }
	        }).start();
	    }
	...
	}
###6.可折叠式标题栏
要实现该效果，需要借用CollapsingToolbarLayout这个工具是一个作用于ToolBar基础之上的布局，只能作为APPbarlayout的子布局来使用，APPbarlayout又是coordinatorlayout的子布局

	<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	    <android.support.design.widget.AppBarLayout
	        android:id="@+id/appBar"
	        android:layout_width="match_parent"
	        android:layout_height="250dp">
	        
			<android.support.design.widget.CollapsingToolbarLayout
	            android:id="@+id/collapsing_toolbar"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"
	            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"	//将主题提到上一层
	            app:contentScrim="?attr/colorPrimary"		//折叠后的背景色
	            app:layout_scrollFlags="scroll|exitUntilCollapsed">	//coordinatorlayout会随着内容详情一块滚动，exitUntilCollapsed表示当coordinatorlayout随着滚动完成之后保留在界面上
		        <ImageView
		            android:id="@+id/fruit_image_view"
		            android:layout_width="match_parent"
		            android:layout_height="match_parent"
		            android:scaleType="centerCrop"
		            app:layout_collapseMode="parallax"/>    //表示折叠过程会产生错位和偏移
		        <android.support.v7.widget.Toolbar
		            android:id="@+id/toolbar"
		            android:layout_width="match_parent"
		            android:layout_height="?attr/actionBarSize"
		            app:layout_collapseMode="pin"/>     //表示折叠过程位置始终保持不变
	        </android.support.design.widget.CollapsingToolbarLayout>
	    </android.support.design.widget.AppBarLayout>
	    <android.support.v4.widget.NestedScrollView		//NestedScrollView在ScrollView基础之上增加了嵌套响应滚动事件功能
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        app:layout_behavior="@string/appbar_scrolling_view_behavior">
	
	        <LinearLayout
	            android:orientation="vertical"
	            android:layout_width="match_parent"
	            android:layout_height="wrap_content">
	            <android.support.v7.widget.CardView
	                android:layout_width="match_parent"
	                android:layout_height="wrap_content"
	                android:layout_marginTop="35dp"
	                android:layout_marginBottom="15dp"
	                android:layout_marginLeft="15dp"
	                android:layout_marginRight="15dp"
	                app:cardCornerRadius="4dp">
	
	                <TextView
	                    android:id="@+id/fruit_content_text"
	                    android:layout_width="wrap_content"
	                    android:layout_height="wrap_content"
	                    android:layout_margin="10dp"/>
	
	            </android.support.v7.widget.CardView>
	        </LinearLayout>

    </android.support.v4.widget.NestedScrollView>
		<android.support.design.widget.FloatingActionButton
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_margin="16dp"
	        android:src="@drawable/comment"
	        app:layout_anchor="@id/appBar"			//指定锚点在APPbarlayout内部
	        app:layout_anchorGravity="bottom|end"/>		//位置在标题栏右下角
	</android.support.design.widget.CoordinatorLayout>
新建fruitActivity

	public class FruitActivity extends AppCompatActivity {

	    public static final String FRUIT_NAME = "fruit_name";
	
	    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_fruit);
	        Intent intent = getIntent();
	        String fruitName = intent.getStringExtra(FRUIT_NAME);
	        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
	        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
	        CollapsingToolbarLayout collapsingToolbar =  findViewById(R.id.collapsing_toolbar);
	        ImageView fruitImageView =  findViewById(R.id.fruit_image_view);
	        TextView fruitContentText =findViewById(R.id.fruit_content_text);
	        setSupportActionBar(toolbar);
	        ActionBar actionBar = getSupportActionBar();
	        if (actionBar != null) {
	            actionBar.setDisplayHomeAsUpEnabled(true);
	        }
	        collapsingToolbar.setTitle(fruitName);
	        Glide.with(this).load(fruitImageId).into(fruitImageView);
	        String fruitContent = generateFruitContent(fruitName);
	        fruitContentText.setText(fruitContent);
	    }
	
	    private String generateFruitContent(String fruitName) {
	        StringBuilder fruitContent = new StringBuilder();
	        for (int i = 0; i < 500; i++) {
	            fruitContent.append(fruitName);
	        }
	        return fruitContent.toString();
	    }
	
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                finish();
	                return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	}
修改fruitadapter

	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
####6.1充分利用系统状态栏
背景图和状态栏融合，需借助Android：fitsSystemWindows属性，将其设置为true，表示控件会出现在状态栏里，必须把所有父布局设置成这个。

	<android.support.design.widget.CoordinatorLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:fitsSystemWindows="true">		//父布局每个必须设置
	
	    <android.support.design.widget.AppBarLayout
	        android:id="@+id/appBar"
	        android:layout_width="match_parent"
	        android:layout_height="250dp"
	        android:fitsSystemWindows="true">		//父布局每个必须设置
	
	        <android.support.design.widget.CollapsingToolbarLayout
	            android:id="@+id/collapsing_toolbar"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"
	            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
	            android:fitsSystemWindows="true"		//父布局每个必须设置
	            app:contentScrim="?attr/colorPrimary"
	            app:layout_scrollFlags="scroll|exitUntilCollapsed">
	
	            <ImageView
	                android:id="@+id/fruit_image_view"
	                android:layout_width="match_parent"
	                android:layout_height="match_parent"
	                android:scaleType="centerCrop"
	                android:fitsSystemWindows="true"		//父布局每个必须设置
	                app:layout_collapseMode="parallax" />
	
	            <android.support.v7.widget.Toolbar
	                android:id="@+id/toolbar"
	                android:layout_width="match_parent"
	                android:layout_height="?attr/actionBarSize"
	                app:layout_collapseMode="pin" />
	        </android.support.design.widget.CollapsingToolbarLayout>
	    </android.support.design.widget.AppBarLayout>
	
	    ...
	</android.support.design.widget.CoordinatorLayout>
然后将状态栏设置为透明，android:statusBarColor指定为@android:color/transparent，这是在v21版本之后才可使用，解决办法：新建values——v21文件夹，新建styles。xml文件

	<resources>
	    <style name="FruitActivityTheme" parent="AppTheme">
	        <item name="android:statusBarColor">@android:color/transparent</item>
	    </style>
	</resources>
在安卓5.0版本之前修改value文件夹下的styles.xml文件

	<resources>

	    <!-- Base application theme. -->
	    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
	        <!-- Customize your theme here. -->
	        <item name="colorPrimary">@color/colorPrimary</item>
	        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
	        <item name="colorAccent">@color/colorAccent</item>
	    </style>
	    <style name="FruitActivityTheme" parent="AppTheme">
	        
	    </style>

	</resources>
最后让fruitactivity使用这个主题，修改Androidmanifest文件

	<activity android:name=".FruitActivity"
            android:theme="@style/FruitActivityTheme">
            
        </activity>

	