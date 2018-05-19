package com.ebuss.tae.ipieptea.tae.controller;

import com.ebuss.tae.ipieptea.tae.bean.XSLTProvider;
import com.ebuss.tae.ipieptea.tae.entity.Transformation;
import com.ebuss.tae.ipieptea.tae.repository.TransformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path="/transform")
public class TransformController {
    @Autowired
    TransformationRepository transformationRepository;

    @Autowired
    XSLTProvider xsltProvider;

    Iterable<Transformation> transformationCache = null;

    @GetMapping(path="/xml2json")
    public @ResponseBody String xml2json (@RequestBody String xml) {
        String rawXml = xml;
        String rawXslt = xsltProvider.getXSLT();

        if(transformationCache == null) {
            transformationCache = transformationRepository.findAll();
        }

        List<Transformation> found = StreamSupport
                .stream(transformationCache.spliterator(), false)
                .filter((t) -> t.getXml().compareTo(xml) == 0)
                .collect(Collectors.toList());
        if(found.size() == 0)
        {
            try {
                Transformer xmlTransformer = TransformerFactory.newInstance().newTransformer(
                        new StreamSource(new StringReader(rawXslt)));
                // perform transformation
                StringWriter result = new StringWriter();
                xmlTransformer.transform(
                        new StreamSource(new StringReader(rawXml)), new StreamResult(result));

                String json = result.getBuffer().toString();

                Transformation transformation = new Transformation();
                transformation.setXml(xml);
                transformation.setJson(json);
                transformationRepository.save(transformation);

                // after adding we recache the list
                transformationCache = transformationRepository.findAll();

                return json;
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }

            return "INVALID XML FORMAT";
        } else if(found.size() == 1) {
            return found.get(0).getJson();
        } else {
            // error: we have more than one saved transform
            throw new RuntimeException();
        }
    }
}
