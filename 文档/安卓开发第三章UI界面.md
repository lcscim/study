#第三章UI开发
###1.常见控件
####1.1.TextView
    <TextView
        android:id="@+id/text_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="This is TextView"
        android:gravity="center"
        android:textSize="24sp"
        android:textColor="#00ff00"/>

####1.2.Button
xml文件
    
	<Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button"
        android:textAllCaps="false"/>
活动中设置点击事件

    Button button = findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //此处添加逻辑
        }
    });
如果按键多可采用如下办法

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	Button button = findViewById(R.id.button);
        	button.setOnClickListener(this);
    	}
    	@Override
    	public void onClick(View v) {
        	switch (v.getId()){
            	case R.id.button:
                	//此处填写逻辑
                	break;
            	default:
                	break;
        	}
    	}
	}	
####1.3.EditText
xml页面

    <EditText
        android:id="@+id/edit_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Type something here"
        android:maxLines="2"/>
活动页面

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    	private EditText editText;
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	Button button = findViewById(R.id.button);
        	editText = findViewById(R.id.edit_text);
        	button.setOnClickListener(this);
    	}

    	@Override
    	public void onClick(View v) {
        	switch (v.getId()){
            	case R.id.button:
                	String inputText = editText.getText().toString();
                	Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
                	break;
            	default:
                	break;
        	}
    	}
	}
####1.4.ImageView
xml页面

    <ImageView
        android:id="@+id/image_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher_background"/>
活动页面

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    	private ImageView imageView;
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	Button button = findViewById(R.id.button);
        	imageView = findViewById(R.id.image_view);
        	button.setOnClickListener(this);
    	}
    	@Override
    	public void onClick(View v) {
        	switch (v.getId()){
            	case R.id.button:
                	imageView.setImageResource(R.drawable.ic_launcher_foreground);
                	break;
            	default:
                	break;
        	}
		}
    }
####1.5.ProgressBar
xml页面

    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        **style="?android:attr/progressBarStyleHorizontal"**	//默认风格为圆形转圈，默认风格可不写；此处将风格指定为水平进度条
        **android:max="100"**/>	//指定水平进度条最大为100
活动页面

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {   
    	private ProgressBar progressBar;
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	Button button = findViewById(R.id.button);
        	progressBar = findViewById(R.id.progress_bar);
        	button.setOnClickListener(this);
    	}
    	@Override
    	public void onClick(View v) {
        	switch (v.getId()){
            	case R.id.button:
                	if (progressBar.getVisibility()==View.GONE){
                    	progressBar.setVisibility(View.VISIBLE);
                	}else {
                    	progressBar.setVisibility(View.GONE);
                	}

					/*int progress = progressBar.getProgress();	//获取当前进度条的值
                	progress +=10;
                	progressBar.setProgress(progress);*/	//重新设置进度条的进度
					//该部分设置水平进度条改变逻辑
                	break;
            	default:
                	break;
        	}
    	}
	}
visibility(可见属性)有三个值：visible(可见，占用布局空间)，invisible（不可见，占用布局空间），gone（不可见，不占用空间）<br/>
####1.6.AlertDialog
一般用于提示一些非常重要的内容或警告信息<br/>

    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);	//创建AlertDialog实例
    dialog.setTitle("This is Dialog");			//设置标题
    dialog.setMessage("Something important");		//设置内容
    dialog.setCancelable(false);		//设置可否用back键取消
    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {		//设置确定按钮的点击事件
        @Override
        public void onClick(DialogInterface dialog, int which) {
    
        }
    });
    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {		//设置取消按钮的点击事件
        @Override
        public void onClick(DialogInterface dialog, int which) {
                        
        }
    });
    dialog.show();
####1.7.ProgressDialog
与AlertDialog类似只是会显示一个进度条，常用于当前操作比较耗时，让用户耐心等待<br/>

    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);  //构建progressdialog对象
	progressDialog.setTitle("This is ProgressDialog");      //设置标题
    progressDialog.setMessage("loading...");    //设置内容
    progressDialog.setCancelable(true);     //设置可否用back键取消，谨慎使用false，操作不当会一直存在
    progressDialog.show();  //调用show方法显示出来
###2. 4种基本布局
布局中可以放置控件也可以放置布局，布局和布局可以嵌套使用。除4种基本布局之外还有其他布局<br/>
####2.1线性布局LinearLayout
1.

		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			android:orientation="horizontal"	//排列方式，horizontal水平，vertical垂直
    		android:layout_width="match_parent"
    		android:layout_height="match_parent">
    		<Button
        		android:id="@+id/button1"
        		android:layout_width="wrap_content"
        		android:layout_height="wrap_content"
        		android:text="Button 1"
        		android:layout_gravity="top"/>	//对齐方式 layout_gravity指定控件在布局中的对齐方式 gravity控件中文本的对齐方式
		</LinearLayout>

2.

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:orientation="horizontal"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent">

    	<EditText
        	android:id="@+id/input_message"
        	android:layout_width="0dp"
        	android:layout_height="wrap_content"
        	android:layout_weight="1"				
			//layout_weight允许使用比例的方式来指定控件的大小，如果父类布局为水平，该控件的width标准写法为0dp。以此类比。
			//水平方向上另一个控件和此控件的layout_weight的值之和作为分母，每个控件的layout_weight的值为分子，得到的值就是该控件占屏幕的多少
        	android:hint="Type something"/>
	 	<Button
        	android:id="@+id/send"
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:text="Send"/>

	</LinearLayout>
####2.2相对布局RelativeLayout
通过相对定位的方式可以将控件出现在布局的任何位置

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"	//和父布局的左侧对齐
        android:layout_alignParentTop="true"	//和父布局的顶部对齐其他类推
        
		**android:layout_above="@+id/button3"			//该控件在指定控件的上方  下方blow
        android:layout_toLeftOf="@+id/button3"			//该控件在指定控件的左侧	右侧toRightOf
		android:layout_alignLeft="@+id/button3"**		//该控件与指定控件的左侧对齐	右侧alignRight 底部alignBottom 顶部alignTop
		
		android:text="button 1"/>
    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:text="button 2"/>
    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="button 3"/>
    <Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentBottom="true"
        android:text="button 4"/>
    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentBottom="true"
        android:text="button 5"/>

	</RelativeLayout>
####2.3.帧布局FrameLayout
帧布局FrameLayout，所有控件默认放在左上角<br/>
可以使用layout_gravity简单的调整控件在布局中的位置
####2.4.百分比布局PercentFrameLayout，PercentRelativeLayout
允许直接指定控件在布局中所占的百分比,该布局为新增布局，为保证在所有安卓版本上使用，需要以下步骤<br/>
1. 打开app/build.gradle，在dependencies闭包中添加如下内容：<br/>

    compile 'com.android.support:percent:26.1.0'	//每当修改build.gradle需要点击sync now同步，将添加的库引入到布局中

2. 在xml文件中

		<android.support.percent.PercentFrameLayout			//由于百分比布局不在内置系统的SDK中需要将路径写出来
    	xmlns:android="http://schemas.android.com/apk/res/android"
    	xmlns:app="http://schemas.android.com/apk/res-auto"			//定义app的命名空间这样才能使用百分比的自定义属性
    	android:layout_width="match_parent"
    	android:layout_height="match_parent">

    	<Button
        	android:id="@+id/button1"
        	android:text="Button 1"
        	android:layout_gravity="left|top"
        	app:layout_widthPercent="50%"		//百分比的自定义属性，指定该控件的宽度为父类布局的50%，下一行同理
        	app:layout_heightPercent="50%"/>	//这里使用app前缀是因为定义了app的命名空间，前缀为Android也是如此
    	<Button
        	android:id="@+id/button2"
        	android:text="Button 2"
        	android:layout_gravity="right|top"
        	app:layout_widthPercent="50%"
        	app:layout_heightPercent="50%"/>
    	<Button
        	android:id="@+id/button3"
        	android:text="Button 3"
        	android:layout_gravity="left|bottom"
        	app:layout_widthPercent="50%"
        	app:layout_heightPercent="50%"/>
    	<Button
        	android:id="@+id/button4"
        	android:text="Button 4"
        	android:layout_gravity="right|bottom"
        	app:layout_widthPercent="50%"
        	app:layout_heightPercent="50%"/>

		</android.support.percent.PercentFrameLayout>
3.PercentRelativeLayout和PercentFrameLayout极其类似，同样继承了RelativeLayout的所有属性，也可以使用app:layout_widthPercent;app:layout_heightPercent来定义控件的宽和高
###3.创建自定义控件
我们所用的所有控件都直接或间接继承自View，所有布局都是直接或间接继承自ViewGroup，ViewGroup是一种特殊的View。View是安卓最基本的一种UI组件，它可以在屏幕上绘制一个矩形区域并且可以相应这块区域的各种事件。我们使用的各种控件其实就是在View的基础上添加了各自特有的功能<br/>
####3.1引入布局
1. 新建标题栏布局如下<br/>

		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:background="@drawable/title_bg"
    	android:layout_width="match_parent"
    	android:layout_height="wrap_content">

    	<Button
        	android:id="@+id/title_back"
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:layout_gravity="center"
        	android:layout_margin="5dp"
        	android:background="@drawable/back_bg"
        	android:text="Back"
        	android:textColor="#fff"/>

    	<TextView
        	android:id="@+id/title_text"
        	android:layout_width="0dp"
        	android:layout_height="match_parent"
        	android:layout_weight="1"
        	android:gravity="center"
        	android:text="Title Text"
        	android:textColor="#fff"
        	android:textSize="24sp" />
    	<Button
        	android:id="@+id/title_edit"
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:layout_gravity="center"
        	android:layout_margin="5dp"
        	android:background="@drawable/edit_bg"
        	android:text="Edit"
        	android:textColor="#fff"/>

		</LinearLayout>
2. 将标题栏布局引入到需要的xml文件中,如下<br/>
		
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent">

    		<include layout="@layout/title"/>		//只需将布局通过include语句引入进来

		</LinearLayout>
3. 将原本的actionbar设置为隐藏<br/>
	
		public class MainActivity extends AppCompatActivity {

    	@Override
    		protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);
        		setContentView(R.layout.activity_main);
        		ActionBar actionBar = getSupportActionBar();		//使用此get方法获得actionbar的实例
        		if (actionBar!=null){		//判断是否为空
            		actionBar.hide();		//如果是空调用actionbar的hide方法将其隐藏
        		}
    		}
		}
####3.2创建自定义控件(以3.1工程举例)
1.新建TitleLayout继承自LinearLayout，让其成为标题栏的控件<br/>

	public class TitleLayout extends LinearLayout {
    	public TitleLayout(Context context, @Nullable AttributeSet attrs) {		//重写父类带有两个参数的构造函数
        	super(context, attrs);
        	LayoutInflater.from(context).inflate(R.layout.title,this);	
			//使用LayoutInflater对布局进行动态加载，from构建出一个LayoutInflater对象，然后该对象调用inflate方法就可以动态加载布局了。inflate的第一个参数为要加载的动态布局，第二个参数是给加载好的布局再添加一个父布局，这里是指titlelayout，于是传入this
    	}
	}
2.在布局文件中添加这个自定义控件<br/>

	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    	<com.example.lvqih.uicustomviews.TitleLayout
        	android:layout_width="match_parent"
        	android:layout_height="wrap_content"/>		//此句就是添加语句
	</LinearLayout>
3.为标题栏中的按钮注册点击事件<br/>

	public class TitleLayout extends LinearLayout implements View.OnClickListener {
    	public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        	super(context, attrs);
        	LayoutInflater.from(context).inflate(R.layout.title,this);
        	Button titleBack = findViewById(R.id.title_back);
        	Button titleEdit = findViewById(R.id.title_edit);
        	titleBack.setOnClickListener(this);
        	titleEdit.setOnClickListener(this);
    	}

    	@Override
    	public void onClick(View v) {
        	switch (v.getId()){
            	case R.id.title_back:
                	((Activity)getContext()).finish();		//结束当前活动和back键作用一样
                	break;
            	case R.id.title_edit:
                	Toast.makeText(getContext(),"You clicked Edit button",Toast.LENGTH_SHORT).show();
                	break;
            	default:
                	break;
        	}
    	}
	}
###4.ListView
####4.1.简单用法
1. 先在xml文件中引入该控件<br/>

    	<ListView
        	android:id="@+id/list_view"
        	android:layout_width="match_parent"
        	android:layout_height="match_parent"/>	//将宽和高设置为match_parent，充满父布局
2. 修改Java代码<br/>
	
		public class MainActivity extends AppCompatActivity {
    		private String[] data = {"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango",
            "Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};
			//创建数组作为数据源
    		@Override
    		protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);
        		setContentView(R.layout.activity_main);
        		ArrayAdapter<String> adapter = new ArrayAdapter<String>	(MainActivity.this,android.R.layout.simple_list_item_1,data);
				//借助arrayadapter将数据源传入，泛型根据数据源进行改变；构造函数根据实际情况选取，此处是三个参数，第一个参数上下文，第二个参数子项布局,第三项是要适配的数据。第二项此处是安卓内置的布局文件，只有一个textview，可用于简单显示一段文本
        		ListView listView = findViewById(R.id.list_view);
        		listView.setAdapter(adapter);	//调用setadapter将构造好的适配器对象传递进去
    		}
		}
####4.2.定制listview界面
1.新建一个实体类作为listview的适配器类型,即作为arraylist指定的泛型<br/>

	public class Fruit {
    	private String name;
    	private int imageId;
    	public Fruit(String name,int imageId){
        	this.name = name;
        	this.imageId = imageId;
    	}

    	public int getImageId() {
        	return imageId;
    	}

    	public String getName() {
        	return name;
    	}
	}
2.自定一个xml布局作为listview的子项布局，即4.1.2中的第二个参数<br/>

	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="wrap_content">

    	<ImageView
        	android:id="@+id/fruit_image"
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content" />
    	<TextView
        	android:id="@+id/fruit_name"
        	android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:layout_gravity="center_vertical"
        	android:layout_marginLeft="10dp"/>

	</LinearLayout>
3.自定适配器继承自ArrayAdapter，泛型为Fruit<br/>

	public class FruitAdapter extends ArrayAdapter<Fruit> {
    	private int resourceId;
    	public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        	super(context, resource, objects);
        	resourceId = resource;
    	}		//重写父类的一组构造函数，此函数有三个参数，分别为上下文，listview的子项布局，数据
    	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        	Fruit fruit = getItem(position);		//获取当前项的fruit实例
        	View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);		//前两个参数已知，第三个参数为false表示让父布局的layout生效，但不会为这个view添加父布局，因为如果添加的话就无法再添加到listview中了。标准写法为false
        	ImageView fruitImage = view.findViewById(R.id.fruit_image);
        	TextView fruitName = view.findViewById(R.id.fruit_name);
        	fruitImage.setImageResource(fruit.getImageId());	
        	fruitName.setText(fruit.getName());
        	return view;
    	}	//该方法在每个子项滑动到屏幕内部时被调用
	}
4.修改代码，将数据传入<br/>

	public class MainActivity extends AppCompatActivity {
		private List<Fruit> fruitList = new ArrayList<>();
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	initFruits();
        	FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        	ListView listView = findViewById(R.id.list_view);
        	listView.setAdapter(adapter);
    	}
    	private void initFruits(){		//初始化数据，传入Fruitlist中
        	for (int i = 0;i<2;i++){
        		Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            	fruitList.add(apple);
            	Fruit banana = new Fruit("Banana",R.drawable.banana_pic);
            	fruitList.add(banana);
            	Fruit orange = new Fruit("Orange",R.drawable.orange_pic);
            	fruitList.add(orange);
            	Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon_pic);
            	fruitList.add(watermelon);
            	Fruit pear = new Fruit("Pear",R.drawable.pear_pic);
            	fruitList.add(pear);
            	Fruit grape = new Fruit("Grape",R.drawable.grape_pic);
            	fruitList.add(grape);
            	Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple_pic);
            	fruitList.add(pineapple);
            	Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry_pic);
            	fruitList.add(strawberry);
            	Fruit cherry = new Fruit("Cherry",R.drawable.cherry_pic);
            	fruitList.add(cherry);
            	Fruit mango = new Fruit("Mango",R.drawable.mango_pic);
            	fruitList.add(mango);

        	}
    	}
	}
####4.3优化listview代码
1.修改FruitAdapter代码解决加载问题<br/>

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view ;
        if (convertView == null){           //第二个参数convertView用于将之前加载好的布局进行缓存，以便以后进行重复使用
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        ImageView fruitImage = view.findViewById(R.id.fruit_image);
        TextView fruitName = view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
2.修改FruitAdapter代码解决重复获取实例问题<br/>

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);        //将viewholder保存到view中
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();        //重新获取viewholder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
####4.4设置listview的点击事件
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
###5.RecyclerView
RecyclerView可以说是一个增强版的ListView，推荐使用RecyclerView<br/>
####5.1.基本用法
1.打开app/build.gradle，在dependencies闭包中添加如下内容：<br/>

    implementation 'com.android.support:recyclerview-v7:26.1.0'		//版本号随实际情况进行修改，修改后进行同步sync now
2.修改布局添加recyclerview控件<br/>

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent">

    	<android.support.v7.widget.RecyclerView			//由于recyclerview不是内置在系统SDK中，所以需要写包的完整路径
        	android:id="@+id/recycler_view"
        	android:layout_width="match_parent"
        	android:layout_height="match_parent"/>

	</LinearLayout>
3.创建和4.2.1和4.2.2相同的fruit类和fruit布局<br/>
4.新建fruitadapter类继承自recyclerview.adapter，并将泛型指定为FruitAdapter.ViewHolder，FruitAdapter.ViewHolder是在fruitadapter中定义的内部类<br/>

    public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    	private List<Fruit> mFruitList;

    	static class ViewHolder extends RecyclerView.ViewHolder{        //此内部类需要继承RecyclerView.ViewHolder
        	ImageView fruitImage;
        	TextView fruitName;
        	public ViewHolder(View itemView) {      //该view就是recyclerview子项最外层的布局
            	super(itemView);
            	fruitImage = itemView.findViewById(R.id.fruit_image);
            	fruitName = itemView.findViewById(R.id.fruit_name);
        	}
    	}
    	public FruitAdapter(List<Fruit> fruitList){     //该构造函数中的方法是将要展示的数据源传递进来并赋值给全局变量	mFruitList
        	mFruitList = fruitList;
    	}
    	//以下三个函数是fruitadapter继承自RecyclerView.Adapter需要重写的
    	@Override
    	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {      
        
        	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        	ViewHolder holder = new ViewHolder(view);
        	return holder;
	    }       //该方法是创建viewholder实例的

    	@Override
    	public void onBindViewHolder(ViewHolder holder, int position) {
        	Fruit fruit = mFruitList.get(position);
        	holder.fruitImage.setImageResource(fruit.getImageId());
        	holder.fruitName.setText(fruit.getName());
    	}       //对recyclerview子项进行赋值，会在每个子项滚动到屏幕内部时被执行

    	@Override
    	public int getItemCount() {
        	return mFruitList.size();
    	}       //告诉recyclerview共有多少子项，并返回
	}
5.修改代码开始使用<br/>

    public class MainActivity extends AppCompatActivity {
    	private List<Fruit> fruitList = new ArrayList<Fruit>();
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	initFruits();
        	RecyclerView recyclerView = findViewById(R.id.recycler_view);
        	LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        	//线性布局
        	recyclerView.setLayoutManager(layoutManager);   //将recyclerview的布局方式设置为线性布局
        	FruitAdapter adapter = new FruitAdapter(fruitList);
        	recyclerView.setAdapter(adapter);
    	}
    	private void initFruits() {
        	for (int i = 0; i < 2; i++) {
            	Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
       	 		fruitList.add(apple);
            	Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            	fruitList.add(banana);
            	Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            	fruitList.add(orange);
            	Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            	fruitList.add(watermelon);
            	Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            	fruitList.add(pear);
            	Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            	fruitList.add(grape);
            	Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            	fruitList.add(pineapple);
            	Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            	fruitList.add(strawberry);
            	Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            	fruitList.add(cherry);
            	Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            	fruitList.add(mango);
        	}
    	}
	}
####5.2实现横向滚动和瀑布流布局
#####5.2.1.横向布局
调整子项布局fruit_item，然后将recyclerview的布局方式改为水平<br/>
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    //线性布局
    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);		//设置线性水平布局
    recyclerView.setLayoutManager(layoutManager);   //将recyclerview的布局方式设置为线性布局
    FruitAdapter adapter = new FruitAdapter(fruitList);
    recyclerView.setAdapter(adapter);
#####5.2.2.瀑布流布局StaggeredGridLayoutManager
1.调整子项布局fruit_item<br/>
2.修改代码<br/>

	public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //创建瀑布流布局实例；StaggeredGridLayoutManager构造函数有两个参数，第一个为指定布局的列数，第二个为排列方向，这里是垂直排列
        recyclerView.setLayoutManager(layoutManager);   //将recyclerview的布局方式设置为瀑布流布局
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name) {   //此方法随机产生长度不同的名字
        Random random = new Random();       //随机数
        int length = random.nextInt(20) + 1;    //随机数在1-20之间
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
#####5.2.3.网格布局GridLayoutManager
网格布局和瀑布流布局相似,关键句如下<br/>

	GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,4);	//此GridLayoutManager构造方法2个参数，第一个上下文，第二个列数
####5.3.Recyclerview点击事件
与listview不同，recyclerview并没有提供setOnItemClickListener方法需要自己创建，一般是修改fruitadapter中的oncreateviewholder方法<br/>

	public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    	private List<Fruit> mFruitList;

    	static class ViewHolder extends RecyclerView.ViewHolder{        //此内部类需要继承RecyclerView.ViewHolder
        	ImageView fruitImage;
        	TextView fruitName;
        	View fruitView;
        	public ViewHolder(View itemView) {      //该view就是recyclerview子项最外层的布局
            	super(itemView);
            	fruitImage = itemView.findViewById(R.id.fruit_image);
            	fruitName = itemView.findViewById(R.id.fruit_name);
            	fruitView = itemView;
        	}
    	}
    	public FruitAdapter(List<Fruit> fruitList){     //该构造函数中的方法是将要展示的数据源传递进来并赋值给全局变量	mFruitList
        	mFruitList = fruitList;
    	}
    	@Override
    	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        	final ViewHolder holder = new ViewHolder(view);
        	holder.fruitView.setOnClickListener(new View.OnClickListener() {    //在这里填写点击逻辑
            	@Override
            	public void onClick(View v) {
                	int position = holder.getAdapterPosition();
                	Fruit fruit = mFruitList.get(position);
                	Toast.makeText(v.getContext(),"you clicked view"+fruit.getName(),Toast.LENGTH_SHORT).show();
            	}
        	});
        	holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            	@Override
            	public void onClick(View v) {
                	int position = holder.getAdapterPosition();
                	Fruit fruit = mFruitList.get(position);
                	Toast.makeText(v.getContext(),"you clicked image"+fruit.getName(),Toast.LENGTH_SHORT).show();
            	}
        	});
        	return holder;
    	}       //该方法是创建viewholder实例的

    	...
	}
###6.最佳实践
见UIBestPractice工程