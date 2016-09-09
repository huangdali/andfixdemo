.class public Lcom/hdl/andfixdemo/ui/MainActivity_CF;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# instance fields
.field private testString:Ljava/lang/String;

.field private tvContent:Landroid/widget/TextView;

.field private tvLog:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 31
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    .line 34
    const-string v0, "\u4fee\u590d\u524d"

    iput-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->testString:Ljava/lang/String;

    return-void
.end method

.method static synthetic access$000(Lcom/hdl/andfixdemo/ui/MainActivity;)Landroid/widget/TextView;
    .locals 1
    .param p0, "x0"    # Lcom/hdl/andfixdemo/ui/MainActivity;

    .prologue
    .line 31
    iget-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvLog:Landroid/widget/TextView;

    return-object v0
.end method

.method private baseConfig()V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 57
    invoke-static {}, Lcom/hdl/andfixdemo/utils/runtimepermissions/PermissionsManager;->getInstance()Lcom/hdl/andfixdemo/utils/runtimepermissions/PermissionsManager;

    move-result-object v2

    new-instance v3, Lcom/hdl/andfixdemo/ui/MainActivity$1;

    invoke-direct {v3, p0}, Lcom/hdl/andfixdemo/ui/MainActivity$1;-><init>(Lcom/hdl/andfixdemo/ui/MainActivity;)V

    invoke-virtual {v2, p0, v3}, Lcom/hdl/andfixdemo/utils/runtimepermissions/PermissionsManager;->requestAllManifestPermissionsIfNecessary(Landroid/app/Activity;Lcom/hdl/andfixdemo/utils/runtimepermissions/PermissionsResultAction;)V

    .line 71
    const-string v2, "config"

    invoke-virtual {p0, v2, v4}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 72
    .local v1, "sPreferences":Landroid/content/SharedPreferences;
    const-string v2, "isFirstRun"

    const/4 v3, 0x1

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 73
    .local v0, "isFirstRun":Z
    if-eqz v0, :cond_0

    .line 74
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    const-string v3, "isFirstRun"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 94
    :cond_0
    return-void
.end method

.method private showData()V
    .locals 4
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.hdl.andfixdemo.ui.MainActivity"
        method = "showData"
    .end annotation

    .prologue
    .line 100
    const/4 v0, 0x1

    .local v0, "i":I
    :goto_0
    const/16 v1, 0xa

    if-gt v0, v1, :cond_0

    .line 101
    iget-object v1, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "--------------content\n"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 100
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 103
    :cond_0
    return-void
.end method


# virtual methods
.method public installPatch(Landroid/content/Context;Ljava/lang/String;)V
    .locals 5
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "url"    # Ljava/lang/String;

    .prologue
    .line 139
    iget-object v2, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvLog:Landroid/widget/TextView;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u4e0b\u8f7d\u5730\u5740\uff1a"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 140
    iget-object v2, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvLog:Landroid/widget/TextView;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u6587\u4ef6\u540d\uff1a"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/"

    invoke-virtual {p2, v4}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v4

    add-int/lit8 v4, v4, 0x1

    invoke-virtual {p2, v4}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 141
    new-instance v1, Lcom/alipay/euler/andfix/patch/PatchManager;

    invoke-direct {v1, p1}, Lcom/alipay/euler/andfix/patch/PatchManager;-><init>(Landroid/content/Context;)V

    .line 142
    .local v1, "patchManager":Lcom/alipay/euler/andfix/patch/PatchManager;
    new-instance v0, Ljava/io/File;

    const-string v2, "/"

    invoke-virtual {p2, v2}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v2

    add-int/lit8 v2, v2, 0x1

    invoke-virtual {p2, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 143
    .local v0, "file":Ljava/io/File;
    new-instance v2, Lcom/lidroid/xutils/HttpUtils;

    invoke-direct {v2}, Lcom/lidroid/xutils/HttpUtils;-><init>()V

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "sdcard/andfix/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    new-instance v4, Lcom/hdl/andfixdemo/ui/MainActivity$3;

    invoke-direct {v4, p0, v0, v1}, Lcom/hdl/andfixdemo/ui/MainActivity$3;-><init>(Lcom/hdl/andfixdemo/ui/MainActivity;Ljava/io/File;Lcom/alipay/euler/andfix/patch/PatchManager;)V

    invoke-virtual {v2, p2, v3, v4}, Lcom/lidroid/xutils/HttpUtils;->download(Ljava/lang/String;Ljava/lang/String;Lcom/lidroid/xutils/http/callback/RequestCallBack;)Lcom/lidroid/xutils/http/HttpHandler;

    .line 173
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 38
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 39
    const v0, 0x7f040019

    invoke-virtual {p0, v0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->setContentView(I)V

    .line 40
    invoke-direct {p0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->baseConfig()V

    .line 43
    const v0, 0x7f0c0050

    invoke-virtual {p0, v0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    .line 44
    const v0, 0x7f0c0051

    invoke-virtual {p0, v0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvLog:Landroid/widget/TextView;

    .line 45
    iget-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    const-string v1, "--------------content\n"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 46
    invoke-direct {p0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->showData()V

    .line 47
    iget-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n\u63d0\u793a\uff1a"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->testString:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 48
    return-void
.end method

.method public onGetData(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 112
    new-instance v0, Lhdl/com/myhttputils/MyHttpUtils;

    invoke-direct {v0}, Lhdl/com/myhttputils/MyHttpUtils;-><init>()V

    const-string v1, "http://121.42.177.185/andfix/checkpatch.json"

    .line 113
    invoke-virtual {v0, v1}, Lhdl/com/myhttputils/MyHttpUtils;->url(Ljava/lang/String;)Lhdl/com/myhttputils/MyHttpUtils;

    move-result-object v0

    const-class v1, Lcom/hdl/andfixdemo/bean/PatchBean;

    .line 114
    invoke-virtual {v0, v1}, Lhdl/com/myhttputils/MyHttpUtils;->setJavaBean(Ljava/lang/Class;)Lhdl/com/myhttputils/MyHttpUtils;

    move-result-object v0

    new-instance v1, Lcom/hdl/andfixdemo/ui/MainActivity$2;

    invoke-direct {v1, p0}, Lcom/hdl/andfixdemo/ui/MainActivity$2;-><init>(Lcom/hdl/andfixdemo/ui/MainActivity;)V

    .line 115
    invoke-virtual {v0, v1}, Lhdl/com/myhttputils/MyHttpUtils;->onExecute(Lhdl/com/myhttputils/CommCallback;)V

    .line 131
    return-void
.end method

.method public onRefresh(Landroid/view/View;)V
    .locals 3
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 106
    iget-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    const-string v1, ""

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 107
    invoke-direct {p0}, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->showData()V

    .line 108
    iget-object v0, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n\u63d0\u793a: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/hdl/andfixdemo/ui/MainActivity_CF;->testString:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 109
    return-void
.end method
