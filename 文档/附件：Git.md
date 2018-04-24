#Git--版本控制工具
###1.创建代码仓库
1.配置身份。windows环境下找到Git Bash打开,输入<br>
	
	git config --global user.name "lcscim"
	git config --global user.mail "lvqihui@live.com"
	//查看是否成功配置同样的语句只需去掉引号内
2.为项目创建一个代码仓库，找到该项目根目录下打开Git Bash，输入以下命令<br/>

	git init		//该句就可创建
创建库完成后就可在该项目下生成一个隐藏的。git文件夹，来记录本地所有Git操作，删除本地仓只需删除该文件即可
###2.提交本地代码
方法为add和commit提交。add用于把想要提交的代码先添加进来，commit真正的执行操作<br/>
	
	git add build.gradle     //添加build.gradle文件
	git add app					//添加app目录下的文件
	git add .					//添加所有文件

	git commit -m "First commit."	//-m后一定要添加提交的描述信息
###3.忽略文件
Git在会检查代码仓库的目录下检查是否存在.gitignore,如果存在的话就一行一行读取该文件中的内容，该文件可以使用通配符“*”，“#”<br/>
安卓工程会自动生成.gitignore文件。一个在根目录下，一个在APP目录下，该目录下基本上存放的都是编写的内容。如果不想把某部分添加到版本控制，就在app/.gitignore添加要忽略的文件路径。最后添加，提交<br/>

	git add .					//添加所有文件
	git commit -m "First commit."	//-m后一定要添加提交的描述信息
###4.查看修改内容
在项目的根目录下输入：

	git status			//可提示是否发生改变
	git diff			//查看所有文件更改的内容，如果要查看某个路径下更改的内容，需要在diff后跟上目标路径
###5.撤销未提交的修改
对于未使用add提交的撤销

	git checkout 		//后面跟上被修改文件的路径名
对于已使用add提交的，先取消添加然后才可撤回提交

	git reset HEAD		//后面跟上被修改文件的路径名
###6.查看提交的记录

	git log
如果只想查看某一条记录，可以在命令中指定该记录的ID并加上-1，如

	git log 某记录的id -1
查看该记录具体具体提交了什么内容再加上-p

	git log 某记录的id -1 -p
###7.Git时间
####7.1分支用法
在主干线上的大版本，在分支线上的小版本，方便修改代码，debug。两条线互不影响<br/>
查看分支:

	git branch
创建一个名为version1.0的分支：

	git branch version1.0
切换分支：
	
	git checkout version1.0
因为两条线互不影响，所以合并所有线修改同个问题，即合并操作：

	git checkout master
	git merge version1.0
不需要某个分支时，可以使用如下代码将其删除：

	git branch -D version1.0
####7.2与远程版本库协作如GitHub
比如远程版本库地址是https://github.com/example/test.git，使用如下代码下载到本地

	git clone https://github.com/example/test.git
将本地代码提交到版本库：
	
	git push origin master		
	//origin指定的是远程版本库的git地址，master指定的是同步到哪个分支上，此代码表示同步到https://github.com/example/test.git的master分支上
将远程版本库上的修改同步到本地：

	git fetch origin master		//同步下来的代码不会合并，会存放在origin/master分支上
通过diff命令来查看远程版本库上修改了哪些东西；

	git diff origin/master
之后再调用merge将origin/master的修改合并到主分支上：

	git merge origin/master
pull命令将远程获取的最新代码下载并合并到本地	：

	git pull origin master	