.class public Lcom/hdl/andfixdemo/MainActivity_CF;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# instance fields
.field private tvContent:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 8
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method

.method private showData()V
    .locals 4
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.hdl.andfixdemo.MainActivity"
        method = "showData"
    .end annotation

    .prologue
    .line 21
    const/4 v0, 0x1

    .local v0, "i":I
    :goto_0
    const/16 v1, 0xa

    if-gt v0, v1, :cond_0

    .line 22
    iget-object v1, p0, Lcom/hdl/andfixdemo/MainActivity_CF;->tvContent:Landroid/widget/TextView;

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

    .line 21
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 24
    :cond_0
    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.hdl.andfixdemo.MainActivity"
        method = "onCreate"
    .end annotation

    .prologue
    .line 13
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 14
    const v0, 0x7f040019

    invoke-virtual {p0, v0}, Lcom/hdl/andfixdemo/MainActivity_CF;->setContentView(I)V

    .line 15
    const v0, 0x7f0c0050

    invoke-virtual {p0, v0}, Lcom/hdl/andfixdemo/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/hdl/andfixdemo/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    .line 16
    iget-object v0, p0, Lcom/hdl/andfixdemo/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    const-string v1, "--------------content----------------\n"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 17
    invoke-direct {p0}, Lcom/hdl/andfixdemo/MainActivity_CF;->showData()V

    .line 18
    return-void
.end method

.method public onRefresh(Landroid/view/View;)V
    .locals 4
    .param p1, "view"    # Landroid/view/View;
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.hdl.andfixdemo.MainActivity"
        method = "onRefresh"
    .end annotation

    .prologue
    .line 27
    iget-object v1, p0, Lcom/hdl/andfixdemo/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    const-string v2, ""

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 28
    invoke-direct {p0}, Lcom/hdl/andfixdemo/MainActivity_CF;->showData()V

    .line 29
    const/4 v0, 0x0

    .line 30
    .local v0, "text":I
    iget-object v1, p0, Lcom/hdl/andfixdemo/MainActivity_CF;->tvContent:Landroid/widget/TextView;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\ntext="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    .line 31
    return-void
.end method
