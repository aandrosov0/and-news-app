package dvx.news.data

import android.R
import dvx.news.data.models.ArticleContent
import dvx.news.data.models.ArticleContentElement
import dvx.news.data.models.ArticleContentImage
import dvx.news.data.models.ArticleContentText
import dvx.news.data.models.ArticleContentTextType
import org.commonmark.node.AbstractVisitor
import org.commonmark.node.Heading
import org.commonmark.node.Image
import org.commonmark.node.Node
import org.commonmark.node.Paragraph
import org.commonmark.node.StrongEmphasis
import org.commonmark.node.Text
import org.commonmark.parser.Parser

internal class ArticleRenderer {
    class DocumentVisitor(private val onAppendBlock: (ArticleContentElement) -> Unit) : AbstractVisitor() {
        override fun visit(heading: Heading?) {
            heading?.let {
                val level = heading.level
                val text = (heading.firstChild as Text?)?.literal ?: ""
                val block = when (level) {
                    1 -> ArticleContentText(
                        id = 0,
                        type = ArticleContentTextType.HEADLINE,
                        value = text
                    )
                    3 ->  ArticleContentText(
                        id = 0,
                        type = ArticleContentTextType.SUBHEADLINE,
                        value = text
                    )
                    else -> throw IllegalArgumentException("Header level $level isn't applicable")
                }
                onAppendBlock(block)
            }
        }

        override fun visit(paragraph: Paragraph?) {
            val paragraphVisitor = ParagraphVisitor(onAppendBlock)
            paragraph?.accept(paragraphVisitor)
        }
    }

    private class ParagraphVisitor(private val onAppendBlock: (ArticleContentElement) -> Unit) : AbstractVisitor() {
        private val stringBuilder = StringBuilder()

        override fun visit(paragraph: Paragraph?) {
            visitChildren(paragraph)

            if (stringBuilder.isNotBlank()) {
                onAppendBlock(
                    ArticleContentText(
                        id = 0,
                        type = ArticleContentTextType.PARAGRAPH,
                        value = stringBuilder.toString()
                    )
                )
            }
        }

        override fun visit(image: Image?) {
            if (stringBuilder.isNotBlank()) {
                onAppendBlock(
                    ArticleContentText(
                        id = 0,
                        type = ArticleContentTextType.PARAGRAPH,
                        value = stringBuilder.toString()
                    )
                )
            }
            stringBuilder.clear()
            image?.let {
                onAppendBlock(
                    ArticleContentImage(
                        id = 0,
                        type = 0,
                        url = it.destination,
                        caption = it.title ?: ""
                    )
                )
            }
        }

        override fun visit(strongEmphasis: StrongEmphasis?) {
            stringBuilder.append("<b>")
            strongEmphasis?.firstChild?.accept(this)
            stringBuilder.append("</b>")
        }

        override fun visit(text: Text?) {
            stringBuilder.append(text?.literal ?: "")
        }
    }

    fun render(document: Node): List<ArticleContentElement> {
        val blocks = mutableListOf<ArticleContentElement>()
        val documentVisitor = DocumentVisitor(blocks::add)
        document.accept(documentVisitor)
        return blocks
    }

    fun renderText(markdown: String): List<ArticleContentElement> {
        val markdownParser = Parser.builder().build()
        val document = markdownParser.parse(markdown)
        return render(document)
    }
}